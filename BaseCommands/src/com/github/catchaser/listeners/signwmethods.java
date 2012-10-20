package src.com.github.catchaser.listeners;

import java.io.File;
import java.util.logging.Logger;

import src.com.github.catchaser.signs.signw;

public class signwmethods{
	    public static final Logger logger = Logger.getLogger("Minecraft");
	    @SuppressWarnings("unused")
		private signw plugin;
	    public boolean warptf = true;
	    public String warpname;
	    
	    
	    public signwmethods(signw sign) {
	    	this.plugin = sign;
	    }

	    
	    public boolean checksw(String warpname) {
			if(new File("plugins/BaseCommands/signwarps/" + warpname).exists()) {
				return true;
			}else if(!(new File("plugins/BaseCommands/signwarps/" + warpname).exists())) {
				return false;
			}else
			return false;
	    	
	    }
}
