package dragonBallZ.model

case class Activity(name: String, energy: Int) extends Fighter[Activity] {

  override def substractEnergy(vs: Int): Activity = this.copy(energy= this.energy - vs)

}
