// Benjamin Clayton
// Program 7, Binary Trees
import java.util.*;

 interface KeyComparable
{
	int keyCompareTo(KeyComparable other);
	
	String toStringKey();
}

class TreeNode
{
	public KeyComparable data;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode()
	{
		data = null;
		left = null;
		right = null;
	}
	
}

class MLBPlayerKey implements KeyComparable
{
	private int _JerseyNumber;
	private String _TeamName;
	
	public MLBPlayerKey(int Jnumber, String Tname)
	{
		_JerseyNumber = Jnumber;
		_TeamName = Tname;
	}
	
	public int getJerseyNumber()
	{
		return _JerseyNumber;
	}
	public String getTeamName()
	{
		return _TeamName;
	}
	
	// How to find if a string is less than another?
	public int keyCompareTo(KeyComparable other)
	{
		int i= 0;
		// If always equal, then it is not a MLBPlayerKey
		if (other instanceof MLBPlayerKey)
		{
			MLBPlayerKey other2 = (MLBPlayer)(other);
			i = _TeamName.compareTo(other2.getTeamName());
			if (i == 0)
			{
				if (_JerseyNumber < other2.getJerseyNumber() )
					i = -1;
				else
					i = 1;
			}
			
		}
		return i;
		
	}
	
	public String toStringKey()
	{
		String s = "#" + _JerseyNumber + "of " +  ShortName();
		return s;
	}
	
	private String ShortName()
	{
		String s = _TeamName.substring(0, Math.min(2, _TeamName.length() ));
		return s;
	}
}

class MLBPlayer extends MLBPlayerKey
{
	
	private String _PlayerName;
	private double _BattingAvg;
	
	public MLBPlayer(int Jnumber, String Tname, String Pname, double BAvg)
	{
		super(Jnumber, Tname);
		_PlayerName = Pname;
		_BattingAvg = BAvg;
		
	}
	
	public String getPlayerName()
	{
		return _PlayerName;
	}
	
	public double getBattingAvg()
	{
		return _BattingAvg;
	}
	
	public int getJerseyNumber()
	{
		return super.getJerseyNumber();
	}
	
	public String getTeamName()
	{
		return super.getTeamName();
	}
	
}
class Table
{
	TreeNode root;
	
	public Table()
	{
		root = null;
	}
	
	public void insert (KeyComparable item)
	{
		if (root == null)
		{
			TreeNode add = new TreeNode();
			add.data = item;
			root = add;
		}
		
		else if ( search(item) == null)
		{
			TraverseIns(root, item);
		}

		return;
			
	}
	
	public void delete(KeyComparable key)
	{
		
	}
	
	public KeyComparable search (KeyComparable key)
	{
		KeyComparable item = null;
		
		return item;
	}
	
	public int getHeight()
	{
		int height = 0;
		
		return height;
	}
	
	public int getSize()
	{
		int size = TraverseGS(root);
		return size;
	}
	
	public double getAverageLevel()
	{
		double avg= 0;
		
		
		return avg;
	}
	
	public void showTree()
	{
		
	}
	
	public String toString()
	{
		String s ="";
		
		s = TraverseTS(root, s);
		
		return s;
		
		
	}
	
	private String TraverseTS( TreeNode node, String s)
	{
		if (node != null)
		{
			if (node.left != null)
			{
				s+= TraverseTS(node.left, s);
			}
		
			if (node != null)
			{
				s+= node.data.toString();
			}
		
			if (node.right != null)
			{
				s+= TraverseTS(node.right, s);
			}
		}
		return s;
		
	}
	
	private int TraverseGS(TreeNode node)
	{
		int size = 0;
		if (node != null)
		{
			size += 1;
		
		
			if (node.left != null)
			{
				size += TraverseGS(node.left);
			}
		
			if (node.right != null)
			{
				size+= TraverseGS(node.right);
			
			}
		}
		return size;
	}
	
	private void TraverseIns(TreeNode node, KeyComparable item)
	{
				
		if (node != null)
		{
			int comp = item.keyCompareTo(node.data);
			
			if (comp == 0) return;
			
			else if (comp < 0)
			{
				if (node.left != null)
					TraverseIns(node.left, item);
				else
				{
					TreeNode newnode = new TreeNode();
					newnode.data = item;
					node.left = newnode;
				}
			}
			
			else if (comp > 0)
			{
				if (node.right != null)
					TraverseIns(node.right, item);
				else
				{
					TreeNode newnode = new TreeNode();
					newnode.data = item;
					node.right = newnode;
				}
			}
			
		return;
	}
	
}


public static class Program7
{
	public static Scanner keyboard = new Scanner( System.in );
	private static Table MLB = new Table();
	
	public static void main(String[] args)
	{

		OperateMenu();	
		
	}
	
	public static void Initialize(Table table)
	{
		table = new Table();
	}
	
	public static void Insert(Table table)
	{
		System.out.println("Who would you like to insert?");
		MLBPlayer item = makeItem();
		table.insert(item);
		
		System.out.println("Player inserted");
	}
	
	public static void Delete(Table table)
	{
		System.out.println("Who would you like to delete?");
		MLBPlayerKey key = makeKey();
		table.delete(key);
		
		System.out.println("Player deleted");
	}
	
	public static void Search(Table table)
	{
		System.out.println("Which player do you want to search for?");
		MLBPlayerKey key = makeKey();
		
		MLBPlayer player = (MLBPlayer)table.search(key);
		
		if (player != null)
			System.out.println("Player found:\n" + player);
		else
			System.out.println("Player not found");
	}
	
	public static void findHeight(Table table)
	{
		System.out.println("Max height is: " + table.getHeight() );
	}
	
	public static void findSize(Table table)
	{
		System.out.println("The size of the table is: " + table.getSize() );
	}
	
	public static void findAvgLvl(Table table)
	{
		System.out.println("Average Level: " + table.getAverageLevel() );
	}
	
	public static void showTree(Table table)
	{
		System.out.println( table.showTree() );
	}
	
	public static void showString(Table table)
	{
		System.out.println(table);
	}
	
	private static void Quit()
	{
		System.out.println("Goodbye");
	}
	private static MLBPlayerKey makeKey()
	{
		int number;
		String team;
		MLBPlayerKey key;
		
		System.out.println("Enter the player's team");
		team = keyboard.next();
		
		System.out.println("Enter the player's jersey number");
		number = keyboard.nextInt();
		
		key = new MLBPlayerKey(number, team);
		
		return key;
		
	}
	
	private static MLBPlayer makeItem()
	{
		MLBPlayer item;
		int number;
		double avg;
		String team, name;
		
		System.out.println("Enter the player's name");
		name = keyboard.next();
		
		System.out.println("Enter the player's team");
		team = keyboard.next();
		
		System.out.println("Enter the player's jersey number");
		number = keyboard.nextInt();
		
		System.out.println("Enter the player's batting average");
		avg = keyboard.nextDouble();
		
		item = new MLBPlayer(number, team, name, avg);
		return item;
	}
	
	private static void ShowMenu()
	{
		System.out.println("Menu: \n" + 
				"1. Reset the table\n" +
				"2. Insert an item\n" +
				"3. Delete an item\n" +
				"4. Search for an item\n" +
				"5. Find the height of the table\n" + 
				"6. Find the size of the table\n" + 
				"7. Find the average level of the items\n" +
				"8. Display the tree\n" +
				"9. Display all the contents of the table"+
				"10. Quit");
	}
	
	private static void OperateMenu()
	{
		ShowMenu();
		
		int MenuOpt = keyboard.nextInt();
		
		if (MenuOpt == 1)
		{
			Initialize(MLB);
		}
		
		else if (MenuOpt == 2)
		{
			Insert(MLB);
		}
		
		else if (MenuOpt == 3)
		{
			Delete(MLB);
		}
		
		else if (MenuOpt == 4)
		{
			Search(MLB);
		}
		
		else if (MenuOpt == 5)
		{
			findHeight(MLB);
		}
		
		else if (MenuOpt == 6)
		{
			findSize(MLB);
		}
		
		else if (MenuOpt == 7)
		{
			findAvgLvl(MLB);
		}
		
		else if (MenuOpt == 8)
		{
			showTree(MLB);
		}
		
		else if (MenuOpt == 9)
		{
			showString(MLB);
		}
		
		else if (MenuOpt == 10)
		{
			Quit();
		}
		
		
		
	}
}}
