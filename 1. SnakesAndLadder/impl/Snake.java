package impl;

public class Snake extends Obstacles {
    public void eat(Player player) {
        /*
         * This will take a player form its current location with validations and will dump it to its tail cell
         * */

        if (this.start != player.currentCell) {
            return;
        }

        player.setCurrentCell(this.end);
    }
}
