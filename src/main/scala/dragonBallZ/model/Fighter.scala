package dragonBallZ.model

trait Fighter [T <: Fighter[T]]{
  val name: String
  val energy: Int

  def fight(energy: Int): Option[T] =
    Option(this.substractEnergy(energy)).filter(_.energy > 0)

  def substractEnergy(energy: Int): T

}