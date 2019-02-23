package dragonBallZ

case class HandleMisionService(mision: Mision) {

  def applyTeamAction(team: Team): List[Fighter] = {
    fight(team.warriors, mision.activities)
  }

  def fight(team: Team, vs: Team): Team = {
    team.warriors match {
      case _ if vs.energy <= 0 => team
      case Nil             => team
      case h::t            =>
        val h2::_ = vs.warriors
        val w = team.substractEnergy(h2)
        val m = vs.substractEnergy(h)
        fight(w, m)
    }
  }

  def fight(fighters: List[Fighter], vs: List[Fighter]): List[Fighter] = {
    fighters match {
      case _ if vs.isEmpty => fighters
      case Nil             => fighters
      case h::t            =>
        val w = h.fight(vs.head).map(_::t).getOrElse(t)
        val m = vs.head.fight(h).map(_::vs.tail).getOrElse(vs.tail)
        fight(w, m)
    }
  }

  def betterTeamForTheMision(t1: Team, t2: Team): String = {

    val moreWarriorsLeft = (t1: Team, t2: Team) =>
      fight(t1.warriors,mision.activities).length
        .compare(fight(t2.warriors,mision.activities).length)

    val moreTeamEnergy = (t1: Team, t2: Team) => t1.energy.compare(t2.energy)

    t1.compare(moreWarriorsLeft, t2)
      .orElse(t1.compare(moreTeamEnergy, t2))
      .map(_.name).getOrElse("Ninguno")
  }
}