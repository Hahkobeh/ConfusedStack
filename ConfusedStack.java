import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConfusedStack {



    public static void main(String [] args){
        LinkedList stack = new LinkedList();
        String [] s = new String[100];
        int i = 0;
        String inputName = args[0];
        String outputName = args[1];

        Pattern push = Pattern.compile("^push[(]([a-z,A-Z,0-9,!,@,#,$,%,^,&,*,(,),\\s]+)?[)]$");
        Pattern natural = Pattern.compile("^push[(][1-9]+[0-9]*[)]$");
        Pattern pop = Pattern.compile("^pop[(][)]$");
        Pattern top = Pattern.compile("^top[(][)]$");



        try {
            BufferedReader br = new BufferedReader(new FileReader(inputName));


            while ((s[i] = br.readLine()) != null){
                i++;
            }
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputName,false));
            for(int j = 0; j < i; j++) {
                //use match for all special cases, then regex and matcher for all others make sure use if else so it only reads line once. push() top() pop()
                Matcher pushMatcher = push.matcher(s[j]);
                Matcher popMatcher = pop.matcher(s[j]);
                Matcher topMatcher = top.matcher(s[j]);
                Matcher naturalMatcher = natural.matcher(s[j]);
                if(topMatcher.find()) {
                    /*

                    If the top element of the stack is 666, top() does not output 666, but 999.

                    If the top of the stack is 7, top() does not output 7, it just removes it from the stack.
                    If the top of the stack is 319, then applying top() outputs 666.

                     */
                    if(stack.top() == 319){
                        bw.write("666");
                        bw.newLine();
                        bw.flush();
                    }else if(stack.top() == 7){
                        stack.pop();
                    }else if(stack.top() == 666){
                        bw.write("999");
                        bw.newLine();
                        bw.flush();
                    }else if(!stack.isEmpty()) {
                        bw.write(Integer.toString(stack.top()));
                        bw.newLine();
                        bw.flush();
                    } else {
                        bw.write("null");
                        bw.newLine();
                        bw.flush();
                    }


                }else if (popMatcher.find()) {
                    /*
                    If the top element of the stack is 666, pop() does not just remove it from the stack (and outputs it), it also removes the next element from the stack, without outputting it (if it exists).
                    If the top of the stack is 7, pop() does not remove it from the stack, but just outputs 7.
                    If the top of the stack is 42, pop() removes all elements of the stack, only outputting 42.

                     */
                    if(stack.top() == 42){
                        while(!stack.isEmpty()){
                            stack.pop();
                        }
                        bw.write("42");
                        bw.newLine();
                        bw.flush();
                    }else if(stack.top() == 7){
                        bw.write("7");
                        bw.newLine();
                        bw.flush();
                    }else if(stack.top() == 666){
                        bw.write(Integer.toString(stack.pop()));
                        bw.newLine();
                        bw.flush();
                        if(!stack.isEmpty()) {
                            stack.pop();
                        }
                    }else if(!stack.isEmpty()) {
                        bw.write(Integer.toString(stack.pop()));
                        bw.newLine();
                        bw.flush();
                    } else {
                        bw.write("Error");
                        bw.newLine();
                        bw.flush();
                        System.exit(1);
                    }

                }else if (pushMatcher.find()){
                    /*

                    push arg must be a natural number
                    push(0) should not result in any change of the stack, except if the stack is empty, in which case it adds 0 to the stack
                    push(666)  adds 666 to stack three times
                    push(3) adds 7 to stack
                    push(13) empties stack (outputting each element in the sequence a series of pops by a non-confused stack would do) and then enters 13 on the stack.

                     */
                    if(s[j].equals("push(0)")){
                        if(stack.isEmpty()){
                            stack.push(0);
                        }
                    } else if(s[j].equals("push(666)")) {
                        stack.push(666);
                        stack.push(666);
                        stack.push(666);
                    }else if(s[j].equals("push(3)")) {
                        stack.push(7);
                    }else if(s[j].equals("push(13)")) {
                        while(!stack.isEmpty()){
                            bw.write(Integer.toString(stack.pop()));
                            bw.newLine();
                        }
                        stack.push(13);
                    }else if(!naturalMatcher.find()){
                        bw.write("Imput error.");
                        bw.newLine();
                        bw.flush();
                        System.exit(1);
                    }else{
                        int number = 0;
                        for (int k = 0; k < s[j].length(); k++) {
                            char temp = s[j].charAt(k);
                            if (temp < '0') {
                                continue;
                            }
                            if(temp > '9'){
                                continue;
                            }
                            number = number * 10 + (temp - '0');
                        }
                        stack.push(number);

                    }



                }else{
                    bw.write("Input error.");
                    bw.newLine();
                    bw.flush();
                    System.exit(1);
                }

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}

class Link {
    private int data;
    private Link next;

    Link(int item, Link nextLink){
        data = item;
        next = nextLink;
    }

    Link(Link nextLink){
        next = nextLink;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

class LinkedList {
    private Link head;
    private Link newLink;
    private int length;

    LinkedList(){
        head = new Link(null);
        length = 0;
    }

    public void incrementLength(){
        length += 1;
    }

    public void decrementLength(){
        length -= 1;
    }

    public int getLength() {
        return length;
    }

    public int getHead() {
        return head.getData();
    }

    public void addLink(int data){
        if(isEmpty()){
            head = new Link(data, null);
            incrementLength();
            return;
        }
        newLink = new Link(data, head);
        head = newLink;
        incrementLength();
        return;
    }

    public int removeLink(){
        int temp = getHead();
        head = head.getNext();
        decrementLength();
        return temp;
    }

    public void clearStack(){
        while ( length != 0){
            removeLink();
        }
    }



    public void push(int data){
        addLink(data);
    }

    public int pop(){
        if(isEmpty()){
            // FILE IO OUTPUTS "Error" ##############################################################################

            System.exit(1);
        }
        return removeLink();

    }

    public boolean isEmpty(){
        if(getLength() == 0){
            return true;
        }
        return false;
    }

    public int top(){
        if (isEmpty()){
            return -1; // FIX THIS TOO IT SHOULD OUTPUT NULL AND CONTINUE ##############################################
        }
        else{
            return head.getData();
        }
    }


}