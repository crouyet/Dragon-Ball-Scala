package dragonBallZ.model

case class Warrior(name: String, lifePoints: Int, energy: Int, movements: List[Movement]) extends Fighter {

  override def substractEnergy(vs: Fighter): Warrior = this.copy(energy= this.energy - vs.energy)

  override def fight(vs: Fighter): Option[Warrior] =
    Option(this.substractEnergy(vs)).filter(_.energy > 0)
}
