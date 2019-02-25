package dragonBallZ.model

trait Fighter {
  val name: String
  val energy: Int

  def fight(vs: Fighter): Option[Fighter]

  def substractEnergy(fighter: Fighter): Fighter

}