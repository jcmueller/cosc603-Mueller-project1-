
/**
 * The Enum HerbState.
 * @author John Mueller
 */
public enum HerbState {
  
  CURED,   // 1
    
    TRANSITION, // 2 
    
    GREEN;  // 3
  
  /**
   * Gets the herb state.  This helps accept input from the user.
   *
   * @param herbStateCode the herb state code
   * @return the herb state
   */
  public HerbState getHerbState(int herbStateCode)
  {
    switch(herbStateCode){
      case 1:
        return CURED;
      case 2:
        return TRANSITION;
      case 3:
        return GREEN;
      default:
        return null;
    }
  }
}
