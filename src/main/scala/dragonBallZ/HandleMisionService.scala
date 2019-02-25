package dragonBallZ

import dragonBallZ.model._

case class HandleMisionService(misions: List[Mision]) {

  def applyTeamAction(team: Team): Option[Team] = {
    doMisions(team)
  }

  def betterTeamForTheMision(t1: Team, t2: Team): Option[Group] = {

    val moreTeamEnergy = (t1: Group, t2: Option[Group]) =>
      t1.energy.compare(t2.map(_.energy).getOrElse(0))

    val moreWarriorsLeft = (t1: Group, t2: Option[Group]) =>
      t1.fighters.length.compare(t2.map(_.fighters.length).getOrElse(0))

    def compare(t1: Option[Group],t2: Option[Group]): Option[Group] = (t1, t2) match {
      case (None, None) => None
      case (_, None) => t1
      case (None, _) => t2
      case _ =>
        t1.flatMap(_.compare(moreWarriorsLeft, t2))
          .orElse(t1.flatMap(_.compare(moreTeamEnergy, t2)))
    }

    compare(doMisions(t1), doMisions(t2))
  }

  private def doMisions(group: Team): Option[Team]  = {
    misions.foldLeft(Option(group))((group: Option[Team], vs: Mision) => fight(group, vs))
  }

  def fight(team: Option[Team], vs: Group): Option[Team] = {
    def loop(group: Option[Team], vs: Option[Group]): Option[Team] = group.flatMap(
      t1 => t1.fighters match {
        case Nil   => group
        case h1::_ => vs.flatMap(
          t2 => t2.fighters match {
            case Nil   => group
            case h2::_ =>
              loop(t1.fight(h2), Some(t2.substractEnergy(h1)))
          })
      })
    loop(team,Some(vs))
  }
}