package dragonBallZ.model

trait Fighter {
  val name: String
  val energy: Int

  def fight(energy: Int): Option[Fighter] =
    Option(this.substractEnergy(energy)).filter(_.energy > 0)

  def substractEnergy(energy: Int): Fighter

}