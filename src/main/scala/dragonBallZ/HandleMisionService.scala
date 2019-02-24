package dragonBallZ

import dragonBallZ.model.{Group, Mision, Team}

case class HandleMisionService(misions: List[Mision]) {

  def applyTeamAction(team: Team): Option[Group] = {
    doMisions(team)
  }

  def betterTeamForTheMision(t1: Team, t2: Team): Option[Group] = {

    val moreTeamEnergy = (t1: Group, t2: Option[Group]) =>
      t1.energy.compare(t2.map(_.energy).getOrElse(0))

    val moreWarriorsLeft = (t1: Group, t2: Option[Group]) =>
      t1.getFighters.length.compare(t2.map(_.getFighters.length).getOrElse(0))

    def compare(t1: Option[Group],t2: Option[Group]): Option[Group] = {
      (t1, t2) match {
        case (None, None) => None
        case (_, None) => t1
        case (None, _) => t2
        case _ =>
          t1.flatMap(_.compare(moreWarriorsLeft, t2))
            .orElse(t1.flatMap(_.compare(moreTeamEnergy, t2)))
      }
    }

    compare(doMisions(t1), doMisions(t2))
  }

  private def doMisions(group: Group): Option[Group]  = {
    misions.foldLeft(Option(group))((group: Option[Group],vs: Group)=> fight(group, vs))
  }

  def fight(group: Option[Group], vs: Group): Option[Group] = {

    def loop(group: Option[Group], vs: Option[Group]): Option[Group] = group.flatMap(
      t1 => t1.getFighters match {
        case Nil   => group
        case h1::_ =>
          vs.flatMap(t2 => t2.getFighters match {
            case Nil   => group
            case h2::_ =>
              val team1 = t1.fight(h2)
              val team2 = t2.substractEnergy(h1)
              loop(team1, Some(team2))
          })
      })

    loop(group,Some(vs))
  }
}