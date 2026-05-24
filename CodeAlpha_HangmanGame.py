import random

# Predefined word list
WORDS = ["python", "coding", "hangman", "computer", "programming"]

# Hangman stages (7 stages: 0 wrong to 6 wrong)
HANGMAN_STAGES = [
    """
       -----
       |   |
           |
           |
           |
           |
    --------
    """,
    """
       -----
       |   |
       O   |
           |
           |
           |
    --------
    """,
    """
       -----
       |   |
       O   |
       |   |
           |
           |
    --------
    """,
    """
       -----
       |   |
       O   |
      /|   |
           |
           |
    --------
    """,
    """
       -----
       |   |
       O   |
      /|\\  |
           |
           |
    --------
    """,
    """
       -----
       |   |
       O   |
      /|\\  |
      /    |
           |
    --------
    """,
    """
       -----
       |   |
       O   |
      /|\\  |
      / \\  |
           |
    --------
    """
]

def play_hangman():
    word = random.choice(WORDS)
    guessed_letters = []
    wrong_guesses = 0
    max_wrong = 6

    print("\n🎮 Welcome to Hangman!")
    print("You have 6 incorrect guesses allowed.\n")

    while wrong_guesses < max_wrong:
        # Display hangman stage
        print(HANGMAN_STAGES[wrong_guesses])

        # Display current word state
        display_word = " ".join([letter if letter in guessed_letters else "_" for letter in word])
        print(f"Word: {display_word}")
        print(f"Wrong guesses left: {max_wrong - wrong_guesses}")
        print(f"Guessed letters: {', '.join(guessed_letters) if guessed_letters else 'None'}")

        # Check if word is fully guessed
        if all(letter in guessed_letters for letter in word):
            print(f"\n🎉 Congratulations! You guessed the word: '{word}'")
            break

        # Get player input
        guess = input("\nEnter a letter: ").lower().strip()

        # Validate input
        if len(guess) != 1 or not guess.isalpha():
            print("❌ Please enter a single letter.")
            continue

        if guess in guessed_letters:
            print(f"⚠️  You already guessed '{guess}'. Try a different letter.")
            continue

        guessed_letters.append(guess)

        if guess in word:
            print(f"✅ '{guess}' is in the word!")
        else:
            wrong_guesses += 1
            print(f"❌ '{guess}' is NOT in the word!")

    else:
        # Player ran out of guesses
        print(HANGMAN_STAGES[max_wrong])
        print(f"\n💀 Game Over! The word was: '{word}'")

    # Ask to play again
    again = input("\nPlay again? (yes/no): ").lower().strip()
    if again == "yes" or again == "y":
        play_hangman()
    else:
        print("\nThanks for playing Hangman! Goodbye 👋")


if __name__ == "__main__":
    play_hangman()
