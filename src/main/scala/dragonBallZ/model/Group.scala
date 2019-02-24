package dragonBallZ.model

trait Group {
  this: Fighter =>
  def getFighters(): List[Fighter]

  override def fight(vs: Fighter): Option[Group]
  override def substractEnergy(fighter: Fighter): Group

}
