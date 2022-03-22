
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #5
CSE 214
Recitation R10, TA: Daniel Calabria
 */



/*
The SceneNode class provides the in-depth details of each scene, including
the title, description, ID #, the static variable numScenes, a parent node,
and the children nodes of each scene.
 */
public class SceneNode {

    private String title; //title of each scene
    private String sceneDescription; // the description for each scene node
    private int sceneID; //the id # associated with each scene
    private SceneNode left; // the left child node for each scene
    private SceneNode middle; // the middle child node for each scene
    private SceneNode right; // the right child node for each scene
    private static int numScenes; // The static variable that will represent the total number of scenes within the game
    private SceneNode parentNode; // the parent for each scene node







    /*constructor for each scene node that will have a title and description associated with it
      entered by the user
     */
    public SceneNode(String title, String sceneDescription) {
        this.numScenes++;
        sceneID = numScenes;
        this.title=title;
        this.sceneDescription=sceneDescription;
    }




    /*
    getter method to retrieve the parent node of a current node
     */
    public SceneNode getParentNode() {
        return parentNode;
    }


    /*
    setter method to assign a current node's parent node to a
    new scene
     */
    public void setParentNode(SceneNode parentNode) {
        this.parentNode = parentNode;
    }



    /*
    getter method to retrieve a scene's title
     */
    public String getTitle() {
        return title;
    }

    /*
    getter method to retrieve a scene's description
     */
    public String getSceneDescription() {
        return sceneDescription;
    }


    /*
    getter method to retrieve the ID # associated with each
    scene, returns sceneID
     */
    public int getSceneID() {
        return sceneID;
    }

    /*
    getter method to retrieve a scene node's left child
     */
    public SceneNode getLeft() {
        return left;
    }



    /*
    setter method to assign a scene node's left child
    to a new scene node
     */
    public void setLeft(SceneNode left) {
        this.left = left;
    }



    /*
    getter method to retrieve a scene node's middle child
     */
    public SceneNode getMiddle() {
        return middle;
    }



    /*
    setter method to assign a scene node's middle child
    to a new scene node
     */
    public void setMiddle(SceneNode middle) {
        this.middle = middle;
    }



    /*
    getter method to retrieve a scene node's right child
    */
    public SceneNode getRight() {
        return right;
    }



    /*
    setter method to assign a scene node's right child
    to a new scene node
     */
    public void setRight(SceneNode right) {
        this.right = right;
    }


    /*
    getter method to retrieve the static variable that represents
    the total number of scenes within the game
     */
    public static int getNumScenes() {
        return numScenes;
    }


    /*
    a method to add a scene into the left
    most position within the tree using  conditional
    statements, throws a  fullSceneException if all three positions are taken
     */
    public void addSceneNode(SceneNode scene) throws FullSceneException{

        if(getLeft()!=null&&getMiddle()!=null&&getRight()!=null){
            throw new FullSceneException("Unable to add because scene is full");
        }
        if(getLeft()==null){
            left = scene;
        }
        else if(getMiddle()==null){
            middle = scene;
        }
        else if(getRight()==null){
            right = scene;
        }


    }



    /*
    boolean method to determine if the game being played
    by the user is coming to an end
     */
    public boolean isEnding() {
        if (getLeft() == null && getMiddle() == null && getRight() == null) {
            return true;
        }
        return false;
    }



    /*
    a display scene method that will help display the scene description
    and the options for the user to select during their game play
     */
    public void displayScene(){
        System.out.println(getSceneDescription());
        if(getLeft()!=null){
            System.out.println("A) " + getLeft().getTitle());
        }
        if(getMiddle()!=null){
            System.out.println("B) " + getMiddle().getTitle());
        }
        if(getRight()!=null){
            System.out.println("C) " + getRight().getTitle());
        }
    }


    /*
    a display full scene method that will display the current scene (cursor) during creation mode
    and will output the scene's details in addition
     */
    public void displayFullScene() {
        //Option S
        if(getLeft()==null&&getRight()==null&&getMiddle()==null){
            System.out.println("Scene ID# " + getSceneID());
            System.out.println("Title: " + getTitle());
            System.out.println("Scene: " + getSceneDescription() + " \nLeads to: NONE");
        }
        else {
            System.out.println("Scene ID# " + getSceneID());
            System.out.println("Title: " + getTitle());
            System.out.println("Scene: " + getSceneDescription() + " \nLeads to: " +
                    getLeft() + ", \n" + getMiddle() + ", \n" + getRight());
        }
    }



    /*
    toString() method to produce the title of each scene with it's scene ID #
     */
    public String toString(){
        //Option P
        System.out.println(getTitle() + "(# " + getSceneID() + ")");
        return "";
    }




}
