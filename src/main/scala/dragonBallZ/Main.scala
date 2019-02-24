package dragonBallZ

import dragonBallZ.model.{Activity, Mision, Team, Warrior}

object Main {
  def main(args: Array[String]) {

    val mision: Mision = Mision("Dormir", List(Activity("Tomar te", 50), Activity("Acostarse", 10), Activity("Apagar la luz", 45))) //30

    val team: Team = Team("Insomnio", List( Warrior("Cami", 100), Warrior("Gaston", 15))) //45

    val team2: Team = Team("Sub21", List(Warrior("Chino", 20), Warrior("Facu", 45))) //65

    val misionHandler: HandleMisionService = HandleMisionService(mision)

    println("ApplyTeamAction: "+ misionHandler.applyTeamAction(team) + "\n\n")
    println("Warriors Left " + team.name + ": " +  misionHandler.fight(team,team2)+ "\n")
    println("Warriors Left " + team2.name + ": " + misionHandler.fight(team2,team)+ "\n\n")
    println("Better Team For The Mision: " + misionHandler.betterTeamForTheMision(team, team2))
  }
}