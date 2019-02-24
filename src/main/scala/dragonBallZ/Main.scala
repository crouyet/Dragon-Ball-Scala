package dragonBallZ

import dragonBallZ.model.{Activity, Mision, Team, Warrior}

object Main {
  def main(args: Array[String]) {

    val dormir: Mision = Mision("Dormir", List(Activity("Tomar te", 50), Activity("Acostarse", 10), Activity("Apagar la luz", 45))) //105
    val comer: Mision = Mision("Comer", List(Activity("Pedir el rappi", 5), Activity("Comer", 10), Activity("Limpiar", 5))) //20

    val team: Team = Team("Adultos Jovenes", List( Warrior("Cami", 100), Warrior("Gaston", 15), Warrior("Pablis", 20), Warrior("Gabi", 45)))

    val team2: Team = Team("Sub21", List(Warrior("Chino", 20), Warrior("Facu", 45), Warrior("Jose", 150)))

    val misionHandler: HandleMisionService = HandleMisionService(List(dormir, comer))

    println("ApplyTeamAction: "+ misionHandler.applyTeamAction(team) + "\n\n")
    println("Warriors Left " + team.name + ": " +  misionHandler.fight(Some(team),team2)+ "\n")
    println("Warriors Left " + team2.name + ": " + misionHandler.fight(Some(team2),team)+ "\n\n")
    println("Better Team For The Mision: " + misionHandler.betterTeamForTheMision(team, team2))
  }
}