package dragonBallZ.model

trait Fighter {
  val name: String
  val energy: Int

  def fight(vs: Fighter): Option[Fighter] =
    Option(this.substractEnergy(vs)).filter(_.energy > 0)

  def substractEnergy(fighter: Fighter): Fighter

}