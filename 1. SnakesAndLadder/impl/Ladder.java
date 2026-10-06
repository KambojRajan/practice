package impl;

public class Ladder extends Obstacles {
    public void rideOn(Player player) {
        /*
         * This will take a player form its current location with validations and will dump it to its end cell
         * */

        if(this.start != player.currentCell){
            return;
        }

        player.setCurrentCell(this.end);
    }
}
