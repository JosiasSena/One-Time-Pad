package josiassena.onetimepad;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * In cryptography, a one-time pad (OTP) is an encryption technique that cannot be cracked if used
 * correctly. In this technique, a plaintext is paired with a random secret key (or pad). Then,
 * each bit or character of the plaintext is encrypted by combining it with the corresponding bit
 * or character from the pad using modular addition. If the key is truly random, is at least as
 * long as the plaintext, is never reused in whole or in part, and is kept completely secret, then
 * the resulting ciphertext will be impossible to decrypt or break. It has also been
 * proven that any cipher with the perfect secrecy property must use keys with effectively the
 * same requirements as OTP keys.However, practical problems have prevented one-time pads
 * from being widely used.
 * <p/>
 * First described by Frank Miller in 1882, the one-time pad was re-invented in 1917 and
 * patented a couple of years later. It is derived from the Vernam cipher, named after Gilbert
 * Vernam, one of its inventors. Vernam's system was a cipher that combined a message with a key
 * read from a punched tape. In its original form, Vernam's system was vulnerable because the key
 * tape was a loop, which was reused whenever the loop made a full cycle. One-time use came later,
 * when Joseph Mauborgne recognized that if the key tape were totally random, then cryptanalysis
 * would be impossible.
 * <p/>
 * The "pad" part of the name comes from early implementations where the key material was
 * distributed as a pad of paper, so that the top sheet could be easily torn off and destroyed
 * after use. For ease of concealment, the pad was sometimes reduced to such a small size that a
 * powerful magnifying glass was required to use it. The KGB used pads of such size that they could
 * fit in the palm of one's hand, or in a walnut shell. To increase security, one-time pads
 * were sometimes printed onto sheets of highly flammable nitrocellulose, so that they could be
 * quickly burned after use.
 *
 * http://en.wikipedia.org/wiki/One-time_pad
 */

public class MainActivity extends ActionBarActivity {
    private EditText messageToEncrypt;
    private TextView encryptedText, key, decryptedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        key = (TextView) findViewById(R.id.key);
        encryptedText = (TextView) findViewById(R.id.encryptedText);
        decryptedText = (TextView) findViewById(R.id.decryptedText);
        messageToEncrypt = (EditText) findViewById(R.id.messageToEncrypt);

        Button encryptButton = (Button) findViewById(R.id.encryptButton);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageToEncrypt.length() != key.length()) {
                    Toast.makeText(MainActivity.this, "The 'Key' must be same length as message", Toast.LENGTH_SHORT).show();
                } else {
                    encrypt(messageToEncrypt.getText().toString(), key.getText().toString());
                }
            }
        });
    }

    private void encrypt(String message, String key) {
        String encryptedMessage = "";

        for (char letter : message.toCharArray()) {
            int encryptedLetter = 0;
//            int encryptedNum = Character.getNumericValue(letter); // turn letter to int
            int encryptedNum = (int) letter; // turn letter to int
            for (char ch : key.toCharArray()) {
//                int encryptedKeyVal =  Character.getNumericValue(ch);
                int encryptedKeyVal = (int) ch;
                encryptedLetter = encryptedNum + encryptedKeyVal;
            }

//            char newValue = Character.forDigit(encryptedLetter, Character.MAX_RADIX); // turn int to char
            char newValue = (char) encryptedLetter; // turn int to char
            encryptedMessage += newValue;
        }

        encryptedText.setText(encryptedMessage);
        decrypt(encryptedMessage, key);
    }

    private void decrypt(String message, String key) {
        String decryptedMessage = "";

        for (char letter : message.toCharArray()) {
            int decryptedLetter = 0;
//            int decryptedNum = Character.getNumericValue(letter); // turn letter to int
            int decryptedNum = (int) letter; // turn letter to int
            for (char ch : key.toCharArray()) {
//                int decryptedKeyVal =  Character.getNumericValue(ch);
                int decryptedKeyVal = (int) ch;
                decryptedLetter = decryptedNum - decryptedKeyVal;
            }

            //char newValue = Character.forDigit(decryptedLetter, Character.MAX_RADIX); // turn int to char
            char newValue = (char) decryptedLetter; // turn int to char
            decryptedMessage += newValue;
        }

        decryptedText.setText(decryptedMessage);
    }
}
