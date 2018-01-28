
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/*
    This class defines an offensive formation
 */
public class Offense extends Formation
{
    public Offense(Team team)
    {
        super(team);
    }
    
    //Adds a player and checks that it is above the y-axis, and that it's position
    //is offensive
    @Override
    public void addPlayer(Player player, TeamPositions teamPosition, Point2D.Double location)
    {
        if (location.y<0)
            {
                throw new IllegalArgumentException(
                "Player "+player.getName()+" has an invalid Y position.");
            }
        if (!teamPosition.getOffense())
                throw new IllegalArgumentException(
                    "Cannot add defensive player to offensive formation");
        super.addPlayer(player, teamPosition, location);
    }
    
    public RiskFactor[] getRiskLevels(Formation opposingTeam)
    {
        Set<Entry<Integer,PlayerEntry>> offenseSet = this.getHashMap().entrySet();
        Set<Entry<Integer,PlayerEntry>> defenseSet = opposingTeam.getHashMap().entrySet();
        return super.getRiskFactor(offenseSet,defenseSet);
    }
}