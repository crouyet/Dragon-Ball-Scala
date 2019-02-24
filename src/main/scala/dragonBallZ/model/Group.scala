package dragonBallZ.model

trait Group extends Fighter {

  def getFighters: List[Fighter]

  override def fight(vs: Fighter): Option[Group] =
    Option(this.substractEnergy(vs)).filter(_.energy > 0)

  override def substractEnergy(fighter: Fighter): Group

  /*def fight(vs: Group): Option[Group] = {
    Option(this.getFighters match {
      case Nil => this
      case _   =>
        vs.getFighters match {
          case Nil   => this
          case h2::_ =>
            this.substractEnergy(h2)
        }
    }).filter(_.energy > 0)
  }*/
}
