package dragonBallZ

case class HandleMisionService(mision: Mision) {

  def applyTeamAction(team: Team): List[Fighter] = {
    doMision(team.warriors, mision.activities)
  }

  def fight(team: Option[Team], vs: Team): Option[Team] = {
    team.flatMap(
      t => t.warriors match {
        case _ if vs.warriors.isEmpty => team
        case h::_            =>
          val h2::_ = vs.warriors
          val t1 = t.fight(h2)
          val t2 = vs.substractEnergy(h)
          fight(t1, t2)
      }
    )
  }

  def doMision(fighters: List[Fighter], vs: List[Fighter]): List[Fighter] = {
    fighters match {
      case _ if vs.isEmpty => fighters
      case Nil             => fighters
      case h::t            =>
        val w = h.fight(vs.head).map(_::t).getOrElse(t)
        val m = vs.head.fight(h).map(_::vs.tail).getOrElse(vs.tail)
        doMision(w, m)
    }
  }

  def betterTeamForTheMision(t1: Team, t2: Team): String = {

    val moreWarriorsLeft = (t1: Team, t2: Team) =>
      doMision(t1.warriors,mision.activities).length
        .compare(doMision(t2.warriors,mision.activities).length)

    val moreTeamEnergy = (t1: Team, t2: Team) => t1.energy.compare(t2.energy)

    t1.compare(moreWarriorsLeft, t2)
      .orElse(t1.compare(moreTeamEnergy, t2))
      .map(_.name).getOrElse("Ninguno")
  }
}