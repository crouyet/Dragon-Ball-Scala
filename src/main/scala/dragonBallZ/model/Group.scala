package dragonBallZ.model

trait Group extends Fighter {

  def fighters: List[Fighter]

  override def substractEnergy(energy: Int): Group

  def compare(f: (Group, Option[Group]) => Int, vs: Option[Group]): Option[Group] =  f(this, vs) match {
    case 0  => None
    case 1  => Some(this)
    case -1 => vs
  }

  override def toString: String = name +": " + fighters.map(f => f.name+ " -> " + f.energy)

  /*def fight(vs: Group): Option[Group] = {
    Option(this.getFighters match {
      case Nil => this
      case _   =>
        vs.getFighters match {
          case Nil   => this
          case h2::_ =>
            this.substractEnergy(h2)
        }
    }).filter(_.energy > 0)
  }*/
}
