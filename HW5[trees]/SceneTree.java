
/*
Haimid Alrobaye
111535129
Haimid.alrobaye@stonybrook.edu
Programming assignment Homework #5
CSE 214
Recitation R10, TA: Daniel Calabria
 */


import java.util.*;

/*
The SceneTree class provides the adventure tree and has the variables and
methods to manipulate and traverse through the tree. In the SceneTree constructor,
a root is instantly created and is the only node that cannot be removed.
 */
public class SceneTree {

    private SceneNode root; //the root/first node of the tree
    private SceneNode cursor; // the cursor/current scene within the scene tree




    /*
    constructor for the scene tree, sets the cursor to the root
    being made in the beginning of the adventure
     */
    public SceneTree(String title,String scene){
        cursor = root = new SceneNode(title,scene);
    }



    //getter method to access the cursor in the tree
    public SceneNode getCursor() {
        return cursor;
    }




    //helper method to return the cursor back to the root instantly
    public void moveToRoot(){
        cursor=root;
    }



    /*
    method to move the cursor back by one position, sets it equal
    to the current node's parent. Throws NoSuchNodeException if cursor is on the root
     */
    public void moveCursorBackwards() throws NoSuchNodeException{
        if(cursor==root){
            throw new NoSuchNodeException("unable to move cursor backwards because there is no previous node");
        }
        cursor = cursor.getParentNode();
    }




    /*
    moves the cursor forward to the option that the user selects,
    if there is no child node at the selected position by the user,
    NoSuchNode exception is thrown
     */
    public void moveCursorForward(String option)throws NoSuchNodeException{
         if((option.equals("A")||option.equals("a"))&& cursor.getLeft()!=null){
            cursor=cursor.getLeft();
         }
         else if((option.equals("B")||option.equals("b"))&& cursor.getMiddle()!=null){
             cursor=cursor.getMiddle();

         }
         else if((option.equals("C")||option.equals("c"))&& cursor.getMiddle()!=null){
             cursor=cursor.getRight();
         }
        else{
            throw new NoSuchNodeException("option does not exist");
        }
    }





    /*
    method to add a node to the tree and assigns a title and description to that
    scene made by the user. If all positions are taken already by scenes,
    throw FullSceneException
     */
    public void addNewNode(String title, String sceneDescription) throws FullSceneException{
        SceneNode newNode;
        SceneNode tempCursor;
        boolean done = false;
            tempCursor=cursor;
            while(!done){
                if(tempCursor.getLeft()==null){
                    newNode=new SceneNode(title,sceneDescription);
                    cursor.setLeft(newNode);
                    newNode.setParentNode(tempCursor);
                    done=true;

                }
                else if(tempCursor.getMiddle()==null){
                    newNode=new SceneNode(title,sceneDescription);
                    cursor.setMiddle(newNode);
                    newNode.setParentNode(tempCursor);
                    done=true;
                }
                else if(tempCursor.getRight()==null){
                    newNode=new SceneNode(title,sceneDescription);
                    cursor.setRight(newNode);
                    newNode.setParentNode(tempCursor);
                    done=true;
                }
                else{
                    throw new FullSceneException("Error, the scene is full");
                }
            }



    }



    /*
    removes the scene at the position selected by the user,
    if there is no node at the position, throw NoSuchNodeException
     */
    public void removeScene(String option) throws NoSuchNodeException{
        SceneNode parentOfCursor = cursor.getParentNode();
        if((option.equals("A")||option.equals("a"))&& cursor.getLeft()!=null){
            System.out.println(cursor.getLeft().getTitle() + " removed");
            cursor.setLeft(null);
            cursor = parentOfCursor;
        }
        else if((option.equals("B")||option.equals("b"))&& cursor.getMiddle()!=null){
            System.out.println(cursor.getMiddle().getTitle() + " removed");
            cursor.setMiddle(null);
            cursor = parentOfCursor;
        }
        else if((option.equals("C")||option.equals("c"))&& cursor.getRight()!=null){
            System.out.println(cursor.getRight().getTitle() + " removed");
            cursor.setRight(null);
            cursor = parentOfCursor;
        }
        else{
            throw new NoSuchNodeException("That option does not exist");
        }

    }



    /*
    moves the currently selected scene to become the child of the scene that has the scene ID number
    selected by the user associated. If the scene is full, throw FullSceneException.
    If there is no node at the selected number, throw NoSuchNodeException
     */
    public void moveScene(int sceneIDToMoveTo)throws NoSuchNodeException,FullSceneException{
        SceneNode ptr = findNodeByID(root,sceneIDToMoveTo);
        SceneNode tempParent = cursor.getParentNode();
        if(ptr==null){
           throw new NoSuchNodeException("option does not exist") ;
        }
        else{
            if(ptr.getLeft()!=null&&ptr.getMiddle()!=null&&ptr.getRight()!=null){
                throw new FullSceneException("error, scene is full");
            }
            else{
                if(ptr.getLeft()==null){
                    ptr.setLeft(cursor);
                }
                else if(ptr.getMiddle()==null){
                    ptr.setMiddle(cursor);
                }
                else if(ptr.getRight()==null){
                    ptr.setRight(cursor);
                }
                cursor.setParentNode(ptr);

                if (tempParent.getLeft() == cursor) {
                    tempParent.setLeft(null);
                }
                else if(tempParent.getMiddle()==cursor){
                    tempParent.setMiddle(null);
                }
                else if(tempParent.getRight()==cursor){
                    tempParent.setRight(null);
                }

            }

        }


    }


    /*
    method that uses recursion to traverse through the tree
    and find a scene by it's ID #
     */
    public SceneNode findNodeByID(SceneNode currentNode, int sceneID){
        SceneNode tmp = null;
        if(currentNode.getSceneID() == sceneID) tmp = currentNode;


        if(currentNode.getLeft() != null){
            tmp = findNodeByID(currentNode.getLeft(), sceneID);
        }
        if(currentNode.getMiddle() != null){
            tmp = findNodeByID(currentNode.getMiddle(), sceneID);
        }

        if(currentNode.getRight() != null){
            tmp = findNodeByID(currentNode.getRight(),sceneID);
        }
        if(tmp != null) return tmp;
        else return null;

    }


    /*
    Method that uses recursion to print out the entire adventure tree and spaces
    out the scenes according to it's depth
     */
    public void printout(SceneNode currentNode, int depth){
        for(int i=1;i<depth;i++){
            System.out.print("\t");
        }
        System.out.println(currentNode.toString());
        if(currentNode.getLeft() != null){
            printout(currentNode.getLeft(), depth+1);
        }
        if(currentNode.getMiddle() != null){
            printout(currentNode.getMiddle(), depth+1);
        }

        if(currentNode.getRight() != null){
            printout(currentNode.getRight(),  depth+1);
        }
    }



    /*
    Prints out the path from the cursor all the way to the root
     */
    public String getPathFromRoot(){
        String list = "";
        int counter = 0;
        SceneNode currentNode = cursor;
        while(currentNode!=root){
            list = list + ", " + currentNode;
            currentNode=currentNode.getParentNode();
            counter=counter+1;
        }
        if(currentNode==root){
            list = list + ", " + currentNode;
            counter=counter+1;
        }
        String[] scenes = list.split(",",counter);
        int x = 0;
        System.out.println("Cursor to root path: ");
        for(int i = scenes.length -1; i >= 0;i--) {
            System.out.println(scenes[x]);
            x++;
        }
        return "";
    }


    /*
    toString() method that uses the printout() method to
    print the adventure scene tree
     */
    public String toString(){
        printout(root,1);
        System.out.print("CURRENTLY SELECTED SCENE: ");
        System.out.println(cursor.toString());
        return "";
    }



}
