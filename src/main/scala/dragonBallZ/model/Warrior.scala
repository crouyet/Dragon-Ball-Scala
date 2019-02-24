package dragonBallZ.model

case class Warrior(name: String, energy: Int) extends Fighter {
  override def substractEnergy(vs: Fighter): Warrior = this.copy(energy = this.energy - vs.energy)
}
