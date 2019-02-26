package dragonBallZ.model

case class Warrior(name: String, lifePoints: Int, energy: Int, movements: List[Movement]) extends Fighter[Warrior] {

  def rest: Int = -100
  def movementToUse: Option[Movement] =
    movements.filter(_.energyNeeded <= energy) match {
      case Nil => None
      case move => move.find(m => m.energyNeeded == move.map(_.energyNeeded).max)
    }

  def substractLifePoints(points: Int): Warrior = this.copy(lifePoints = this.lifePoints - points)

  override def substractEnergy(energy: Int): Warrior = this.copy(energy = this.energy - energy)

  override def fight(energy: Int): Option[Warrior] =
    Option(substractLifePoints(energy)).filter(_.lifePoints > 0).flatMap(w =>
    movementToUse match {
      case None    => Option(w.substractEnergy(rest))
      case Some(m) => Option(w.substractEnergy(m.energyNeeded)).filter(_.energy > 0)
    }
  )
}