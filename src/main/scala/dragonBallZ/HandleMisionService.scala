package dragonBallZ

import dragonBallZ.model.{Group, Mision, Team}

case class HandleMisionService(misions: List[Mision]) {

  def applyTeamAction(team: Team): Option[Group] = {
    doMisions(Some(team),misions)
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

  def doMisions(group: Option[Group], misions: List[Group]): Option[Group]  = {
    misions.foldLeft(group)((group: Option[Group],vs: Group)=> fight(group, vs))
  }

  def betterTeamForTheMision(t1: Team, t2: Team): String = {

    val moreWarriorsLeft = (t1: Team, t2: Team) =>
      doMisions(Some(t1),misions).map(_.getFighters.length).getOrElse(0)
        .compare(doMisions(Some(t2),misions).map(_.getFighters.length).getOrElse(0))

    val moreTeamEnergy = (t1: Team, t2: Team) => t1.energy.compare(t2.energy)

    t1.compare(moreWarriorsLeft, t2)
      .orElse(t1.compare(moreTeamEnergy, t2))
      .map(_.name).getOrElse("Ninguno")
  }
}