package dragonBallZ

case class HandleMisionService(mision: Mision) {

  def applyTeamAction(team: Team): Option[Goup] = {
    fight(team, mision)
  }

  def fight(group: Goup, vs: Goup): Option[Goup] = {

    def loop(group: Option[Goup], vs: Option[Goup]): Option[Goup] = group.flatMap(
      t1 => t1.getFighters() match {
        case Nil   => group
        case h1::_ =>
          vs.flatMap(t2 => t2.getFighters() match {
            case Nil   => group
            case h2::_ =>
              val team1 = t1.fight(h2)
              val team2 = t2.substractEnergy(h1)
              loop(team1, Some(team2))
          })
      })

      loop(Some(group),Some(vs))
  }

  def doMision(fighters: List[Fighter], vs: List[Fighter]): List[Fighter] = {
    fighters match {
      case Nil     => fighters
      case h1::t1  =>
        vs match {
          case Nil => fighters
          case h2 :: t2 =>
            val team1 = h1.fight(h2).map(_ :: t1).getOrElse(t1)
            val team2 = h2.fight(h1).map(_ :: t2).getOrElse(t2)
            doMision(team1, team2)
        }
    }
  }

  def betterTeamForTheMision(t1: Team, t2: Team): String = {

    val moreWarriorsLeft = (t1: Team, t2: Team) =>
      fight(t1,mision).map(_.getFighters().length).getOrElse(0)
        .compare(fight(t2,mision).map(_.getFighters().length).getOrElse(0))

    val moreTeamEnergy = (t1: Team, t2: Team) => t1.energy.compare(t2.energy)

    t1.compare(moreWarriorsLeft, t2)
      .orElse(t1.compare(moreTeamEnergy, t2))
      .map(_.name).getOrElse("Ninguno")
  }
}