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

  def fight(team: Option[Team], mision: Mision): Option[Team] = {
    def loop(team: Option[Team], mision: Option[Mision]): Option[Team] = team.flatMap(
      t => t.fighters match {
        case Nil   => team
        case _ if mision.isEmpty  => team
        case _ => mision.flatMap(
          m => {
            val h2::_ = m.fighters
            val team = t.fight(h2.energy)
            val energy = team.flatMap(_.movementToUse.map(_.damage)).getOrElse(0)
            val mision = m.fight(energy)
            loop(team, mision)
          })
      })
    loop(team,Some(mision))
  }
}