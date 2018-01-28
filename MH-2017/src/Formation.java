/*
    This abstract class contains a list of players and their positions.

    Instance Data:
        roster: An arraylist which contains a list of all the players on a team
        along with their position.
        team: The team which the roster uses.
 */

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public abstract class Formation
{
    private HashMap<Integer,PlayerEntry> roster; //change variable name
    private Team team;
    
    public Formation(Team team)
    {
        this.team=team;
        roster = new HashMap<Integer,PlayerEntry>();
    }
    
    //Adds a new player by taking in their role and their location
    public void addPlayer(Player player, TeamPositions teamPosition, Point2D.Double location)
    {
        if (!team.containsPlayer(player))
            throw new IllegalArgumentException(
                "Player " +player.getName() + " not found in team.");
        roster.put(player.getNumber(),(new PlayerEntry(player,teamPosition,location)));
    }
    
    //Takes a number and returns the player associated with it
    public Player getPlayer(int number)
    {
        return roster.get(number).getPlayer();
    }
    
    //Takes a number and returns the position associated with it
    public TeamPositions getTeamPosition(int number)
    {
        return roster.get(number).getTeamPositions();
    }
    
    //Takes a number and returns the location associated with it
    public Point2D.Double getLocation(int number)
    {
        return roster.get(number).getLocation();
    }

    //Takes a number and removes the associated PlayerEntry from the hashmap.
    public PlayerEntry removePlayer(int number)
    {
        return roster.remove(number);
    }
    
    //Returns the HashMap
    public HashMap<Integer,PlayerEntry> getHashMap()
    {
        return roster;
    }
    
    //Returns an array with all player entries associated with a risk value.
    abstract public RiskFactor[] getRiskLevels(Formation opposingTeam);
    
    public     RiskFactor[] getRiskFactor(Set<Entry<Integer, PlayerEntry>> offenseSet, Set<Entry<Integer, PlayerEntry>> defenseSet)
    {
        RiskFactor[] output = new RiskFactor[offenseSet.size()];
        
        int i=0;
        for (Map.Entry<Integer,PlayerEntry> ourEntry : offenseSet)
        {
            int thisDangerValue = 0;
            for (Map.Entry<Integer,PlayerEntry> theirEntry : defenseSet)
            {
                for (TeamPositions position :ourEntry.getValue().getTeamPositions().getRiskPositions())
                {
                    if (position.equals(theirEntry.getValue().getTeamPositions()))
                    {
                        thisDangerValue+=ourEntry.getValue().getPlayer().riskAgainst(theirEntry.getValue().getPlayer());
                    }
                }
            }
            output[i]=new RiskFactor(thisDangerValue,ourEntry.getValue());
            i++;
        }
        return output;
    }

    
}
