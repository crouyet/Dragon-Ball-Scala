package dragonBallZ.model

case class Activity(name: String, energy: Int) extends Fighter {

  override def substractEnergy(vs: Int): Activity = this.copy(energy= this.energy - vs)

  override def fight(vs: Int): Option[Activity] =
    Option(this.substractEnergy(vs)).filter(_.energy > 0)
  //warrior.movementToUse.map(_.damage).getOrElse(0)

}
