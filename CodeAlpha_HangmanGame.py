import random

WORDS = ["python", "coding", "hangman", "computer", "programming"]

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

    print("Welcome to Hangman!")
    print("You have 6 incorrect guesses allowed.")

    while wrong_guesses < max_wrong:
        print(HANGMAN_STAGES[wrong_guesses])

        display_word = " ".join([letter if letter in guessed_letters else "_" for letter in word])
        print("Word:", display_word)
        print("Wrong guesses left:", max_wrong - wrong_guesses)
        print("Guessed letters:", ", ".join(guessed_letters) if guessed_letters else "None")

        if all(letter in guessed_letters for letter in word):
            print("Congratulations! You guessed the word:", word)
            break

        guess = input("Enter a letter: ").lower().strip()

        if len(guess) != 1 or not guess.isalpha():
            print("Please enter a single letter.")
            continue

        if guess in guessed_letters:
            print("You already guessed that letter. Try a different one.")
            continue

        guessed_letters.append(guess)

        if guess in word:
            print("Correct!")
        else:
            wrong_guesses += 1
            print("Wrong!")

    else:
        print(HANGMAN_STAGES[max_wrong])
        print("Game Over! The word was:", word)

    again = input("Play again? (yes/no): ").lower().strip()
    if again == "yes" or again == "y":
        play_hangman()
    else:
        print("Thanks for playing. Goodbye!")


if __name__ == "__main__":
    play_hangman()
