package controller.input;

import util.Direction;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyCompoundHelper {

    private Map<Direction, char[]> compoundKeys;

    public KeyCompoundHelper() {
        this.compoundKeys = new HashMap<>();
        compoundKeys.put(Direction.NORTH_EAST, new char[] {'z', 'd'});
        compoundKeys.put(Direction.NORTH_WEST, new char[] {'z', 'q'});
        compoundKeys.put(Direction.SOUTH_EAST, new char[] {'s', 'd'});
        compoundKeys.put(Direction.SOUTH_WEST, new char[] {'s', 'q'});
    }

    public List<KeyEvent> translateKeys(List<KeyEvent> translate) {
        List<Character> characterList = new ArrayList<>(translate.stream().map(KeyEvent::getKeyChar).toList());

        if(characterList.contains('z') && characterList.contains('d'))
            return replace('z', 'd', DirectionInput.getKeyInput(Direction.NORTH_EAST), translate);
        if(characterList.contains('z') && characterList.contains('q'))
            return replace('z', 'q', DirectionInput.getKeyInput(Direction.NORTH_WEST), translate);
        if(characterList.contains('s') && characterList.contains('d'))
            return replace('s', 'd', DirectionInput.getKeyInput(Direction.SOUTH_EAST), translate);
        if(characterList.contains('s') && characterList.contains('q'))
            return replace('s', 'q', DirectionInput.getKeyInput(Direction.SOUTH_WEST), translate);

        return translate;
    }

    List<KeyEvent> replace(char a, char b, KeyEvent replacement, List<KeyEvent> toTranslate) {
        List<KeyEvent> result = new ArrayList<>(
                toTranslate.stream().filter(
                    keyEvents -> !String.valueOf(keyEvents.getKeyChar()).equals(String.valueOf(a))
                                &&
                                 !String.valueOf(keyEvents.getKeyChar()).equals(String.valueOf(b))
                )
                .toList()
        );

        result.add(replacement);

        return result;
    }

    public List<KeyEvent> removeCombination(List<KeyEvent> keyEventList, char releasedCharacter) {
        switch(releasedCharacter) {

            case 'z':
                if(listContainsDirection(keyEventList, Direction.NORTH_EAST)
                        || listContainsDirection(keyEventList, Direction.NORTH_WEST)) {

                }


        }
    }

    boolean listContainsDirection(List<KeyEvent> events, Direction selected) {
        return events.stream().map(KeyEvent::getKeyChar).anyMatch(
                keyChar -> String.valueOf(keyChar).equals(
                    String.valueOf(DirectionInput.getKeyInput(selected).getKeyChar())
                )
        );
    }

    /**
     * Returns a second key for the given compound
     * @param compound the compound direction consisting of two keys
     * @param firstKey the first key of a compound
     * @return the second key of the compound
     */
    KeyEvent getKeyForCompound(Direction compound, KeyEvent firstKey) {
        if(this.compoundKeys.containsKey(compound))
            return String.valueOf(firstKey.getKeyChar()).equals(String.valueOf(this.compoundKeys.get(compound)[0]))
                    ?
                    new KeyEvent(null, 1, 20, 1, 10, this.compoundKeys.get(compound)[1]) :
                    new KeyEvent(null, 1, 20, 1, 10, this.compoundKeys.get(compound)[0]);
    }

}
