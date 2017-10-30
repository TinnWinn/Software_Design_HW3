package hw3;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main implements Comparable  {
	
	
    protected boolean binSearch(Comparable[] List, Comparable key){
        int low = 0;
        int high = List.length - 1;
        int middle;

        while(low <= high)
        {
        	middle = (high - low) / 2 + low;

            if(List[middle].compareTo(key) < 0)
            { 
            	low = middle + 1;
            }
            else if(List[middle].compareTo(key) > 0) 
            { 
            	high = middle - 1;
            }
            else 
            { 
                return true;
            }
        }
        return false; 
    }
    
    public int compareTo(Object o) 
    {
        return 0;
    }
	public static void main(String[] args) {
		
		Options op = new Options();
		Option typeOp = Option.builder("t").required(true).longOpt("type").hasArg().numberOfArgs(1).build();
		Option keyOp = Option.builder("k").required(true).longOpt("key").hasArg().numberOfArgs(1).build();
		Option listOp = Option.builder("l").required(true).longOpt("list").hasArgs().valueSeparator(' ').build();
		op.addOption(typeOp).addOption(keyOp).addOption(listOp);
		
		CommandLineParser parse = new DefaultParser();
		CommandLine cmd;
		
		try{
			cmd = parse.parse(op, args);
			Main main = new Main();
		
			if(cmd.getOptionValue("type").equals("i"))
				{
					int nKey = Integer.parseInt(cmd.getOptionValue("key"));
					Integer [] numArray = new Integer[cmd.getOptionValues("list").length];
					String[] listOfString = cmd.getOptionValues("list");
					int i = 0;
					for(String element : listOfString)
					{
						numArray[i] = Integer.parseInt(element);
						i++;
					}
					if(main.binSearch(numArray,nKey) == true)
					{
						System.out.println(1);
					}
					else{
						System.out.println(0);
					}
				}
			else if(cmd.getOptionValue("type").equals("s"))
			{
				String[] listOfString = cmd.getOptionValues("list");
				String sKey = cmd.getOptionValue("key");
				if(main.binSearch(listOfString,sKey) == true)
				{
					System.out.println(1);
				}
				else{
					System.out.println(0);
				}
			}
			else{
				throw new ParseException("Exception Thrown");
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}

}
