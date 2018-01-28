import java.util.ArrayList;
/*
    
    Instance Data:
        roster: An arraylist which contains a list of all the players on a team.
 */

public class Team
{
    private ArrayList<Player> roster;
    private String teamName;

    public Team(String teamName)
    {
        this.teamName = teamName;

        roster = new ArrayList<Player>();
    }

    public void addPlayer(Player newPlayer)
    {
        roster.add(newPlayer);
    }
    
    public void removePlayer(Player playerToRemove)
    {
        roster.remove(playerToRemove);
    }

    public String getTeamName()
    {
        return this.teamName;
    }

    public boolean containsPlayer(Player checkPlayer)
    {
        return roster.contains(checkPlayer);
    }
}
