class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> have = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();

        //needed
        for(int i=0; i<ransomNote.length(); i++){
            char ch = ransomNote.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        // currently have (magazine)
        for(int i=0; i<magazine.length(); i++){
            char ch = magazine.charAt(i);
            have.put(ch, have.getOrDefault(ch, 0) + 1);
        }
        // needed = have
        for(char ch : need.keySet()){
            int fneed = need.get(ch); // freq of needed char
            int fhave = have.getOrDefault(ch, 0);
            if(fhave<fneed){
                return false;
            }
        }
        return true;

    }
}