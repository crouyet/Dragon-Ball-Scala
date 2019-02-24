package dragonBallZ

trait Goup {
  this: Fighter =>
  def getFighters(): List[Fighter]

  override def fight(vs: Fighter): Option[Goup]
  override def substractEnergy(fighter: Fighter): Goup
}

trait Fighter {
  val name: String
  val energy: Int

  def fight(vs: Fighter): Option[Fighter] =
    Option(this.substractEnergy(vs)).filter(_.energy > 0)

  def substractEnergy(fighter: Fighter): Fighter

  def compare(f: (Fighter, Fighter) => Int, vs: Fighter): Option[Fighter] =  f(this, vs) match {
    case 0  => None
    case 1  => Some(this)
    case -1 => Some(vs)
  }
}