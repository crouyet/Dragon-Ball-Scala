package dragonBallZ

import dragonBallZ.model._

case class HandleMisionService(misions: List[Mision]) {

  def applyTeamAction(team: Team): Option[Team] = {
    doMisions(team)
  }

  def betterTeamForTheMision(t1: Team, t2: Team): Option[Team] = {

    val moreTeamEnergy = (t1: Team, t2: Option[Team]) =>
      t1.energy.compare(t2.map(_.energy).getOrElse(0))

    val moreWarriorsLeft = (t1: Team, t2: Option[Team]) =>
      t1.warriors.length.compare(t2.map(_.warriors.length).getOrElse(0))

    def compare(t1: Option[Team],t2: Option[Team]): Option[Team] = (t1, t2) match {
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
      t => t.warriors match {
        case Nil => team
        case _   => mision match {
          case None    => team
          case Some(m) =>
            m.activities match {
              case Nil   => team
              case h2::_ =>
                val team = t.fight(h2.energy)
                val energy = team.flatMap(_.movementToUse.map(_.damage)).getOrElse(0)
                val mision = m.fight(energy)
                loop(team, mision)
            }
        }
      })
    loop(team,Some(mision))
  }
}