/**
 *  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package klout.types;

/**
 * Represents a Klout User.
 * @author aidan
 *
 */
public class User {

	private String uname;
	private double kscore;
	
	/**
	 * The Users constructor
	 * @param uname their uname
	 * @param kscore their kscore.
	 */
	public User(String uname, double kscore){
		this.uname = uname;
		this.kscore = kscore;
	}
	
	public double getKScore(){
		return this.kscore;
	}
	
	public void setKScore(double kscore){
		this.kscore = kscore;
	}
	
	public String getUname(){
		return this.uname;
	}
	
	public void setUname(String uname){
		this.uname = uname;
	}
}
