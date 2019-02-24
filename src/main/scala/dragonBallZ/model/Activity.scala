package dragonBallZ.model

case class Activity(name: String, energy: Int) extends Fighter {
  override def substractEnergy(vs: Fighter): Activity = this.copy(energy= this.energy - vs.energy)
}
