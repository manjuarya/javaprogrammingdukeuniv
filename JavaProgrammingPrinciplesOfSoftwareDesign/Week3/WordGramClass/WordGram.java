
public class WordGram {
    public String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }
    

    public String toString(){ 
        String ret = "";
        
        for (int k=0; k<myWords.length; k++) {
            ret += myWords[k] + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k<myWords.length; k++) {
            if (! myWords[k].equals(other.wordAt(k))) {
                return false;
            }          
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        StringBuilder sb = new StringBuilder();
        sb.append(out.toString());
        sb.append(" ");
        sb.append(word);
        String st = sb.toString();
        String[] in = st.split("\\s+");
        out = new WordGram(in, 1, myWords.length);
        /*for (int k=0; k<myWords.length-1; k++) {
            out.wordAt(k) = myWords[k+1];            
        }
        int lastIndex = myWords.length-1;
        out[lastIndex] = word;*/
        // you lose the first word
        // TODO: Complete this method
        return out;
    }

    public int hashCode() {
        String st = myWords.toString();
        return st.hashCode();
    } 
}