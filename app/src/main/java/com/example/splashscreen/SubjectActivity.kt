package com.example.splashscreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splashscreen.databinding.ActivitySubjectBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.Collections.shuffle


class SubjectActivity : AppCompatActivity() {
    private val usedQuestionIndices = mutableListOf<Int>()
    private lateinit var binding: ActivitySubjectBinding
    private lateinit var quizModelList: MutableList<QuizModel>
    private lateinit var adapter: QuizListAdapter
    private lateinit var allQuestionsMap: Map<String, List<QuestionModel>> // Map of subject id to list of questions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        quizModelList = mutableListOf()
        getDataFromJsonString(jsonString = toString())
    }

    private fun setupRecyclerView() {
        binding.progressBar.visibility = View.GONE
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getDataFromJsonString(jsonString: String) {
        binding.progressBar.visibility = View.VISIBLE
        val jsonString = """[
            
                {
                    "id":"1",
                    "title":"General Knowledge",
                    "subtitle":"History, Geography, Science",
                    "time":"10",
                    "questionList":[
                    {
                        "question":"Which planet is known as Red planet?",
                        "options":["Mars","Earth","Saturn","Mercury"
                        ],
                        "correct":"Mars"
                    },
                    {
                        "question": "Which country is known as the 'Land of the Rising Sun'?",
                        "options": ["China", "Japan", "South Korea", "Thailand"],
                        "correct": "Japan"
                    },
                    {
                        "question": "Who painted the Mona Lisa?",
                        "options": ["Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"],
                        "correct": "Leonardo da Vinci"
                    },
                    {
                        "question": "Which river is the longest in the world?",
                        "options": ["Amazon", "Nile", "Mississippi", "Yangtze"],
                        "correct": "Nile"
                    },
                    {
                        "question": "Which of the following is the largest planet in our solar system?",
                        "options": ["Jupiter", "Saturn", "Earth", "Mars"],
                        "correct": "Jupiter"
                    },
                    {
                        "question": "Who wrote '1984'?",
                        "options": ["George Orwell", "Aldous Huxley", "F. Scott Fitzgerald", "Ernest Hemingway"],
                        "correct": "George Orwell"
                    },
                    {
                        "question": "What is the capital of Australia?",
                        "options": ["Sydney", "Melbourne", "Canberra", "Brisbane"],
                        "correct": "Canberra"
                    },
                    {
                        "question": "Which element has the chemical symbol 'Na'?",
                        "options": ["Nitrogen", "Neon", "Sodium", "Nickel"],
                        "correct": "Sodium"
                    },
                    {
                        "question": "In which year did the Titanic sink?",
                        "options": ["1912", "1905", "1920", "1935"],
                        "correct": "1912"
                    },
                    {
                        "question": "Who is known as the 'Father of Modern Physics'?",
                        "options": ["Isaac Newton", "Albert Einstein", "Galileo Galilei", "Niels Bohr"],
                        "correct": "Albert Einstein"
                    },
                    {
                        "question": "Which planet is known as the 'Morning Star' or the 'Evening Star'?",
                        "options": ["Venus", "Mercury", "Mars", "Jupiter"],
                        "correct": "Venus"
                    },
                    {
                        "question": "Who wrote 'The Great Gatsby'?",
                        "options": ["F. Scott Fitzgerald", "Ernest Hemingway", "John Steinbeck", "J.D. Salinger"],
                        "correct": "F. Scott Fitzgerald"
                    },
                    {
                        "question": "What is the smallest bone in the human body?",
                        "options": ["Femur", "Stapes", "Tibia", "Humerus"],
                        "correct": "Stapes"
                    },
                    {
                        "question": "Which animal is known as the 'King of the Jungle'?",
                        "options": ["Lion", "Tiger", "Elephant", "Giraffe"],
                        "correct": "Lion"
                    },
                    {
                        "question": "Who painted the ceiling of the Sistine Chapel?",
                        "options": ["Michelangelo", "Leonardo da Vinci", "Raphael", "Donatello"],
                        "correct": "Michelangelo"
                    },
                    {
                        "question": "What is the capital of France?",
                        "options": ["Madrid", "Berlin", "Paris", "Rome"],
                        "correct": "Paris"
                    },
                    {
                        "question": "Who was the first woman to win a Nobel Prize?",
                        "options": ["Marie Curie", "Rosalind Franklin", "Mother Teresa", "Ada Lovelace"],
                        "correct": "Marie Curie"
                    },
                    {
                        "question": "What is the currency of Japan?",
                        "options": ["Yuan", "Yen", "Euro", "Dollar"],
                        "correct": "Yen"
                    },
                    {
                        "question": "Who discovered penicillin?",
                        "options": ["Alexander Fleming", "Louis Pasteur", "Marie Curie", "Antoine Lavoisier"],
                        "correct": "Alexander Fleming"
                    },
                    {
                        "question": "What is the national flower of India?",
                        "options": ["Lotus", "Rose", "Jasmine", "Sunflower"],
                        "correct": "Lotus"
                    },
                    {
                        "question": "Who wrote 'Pride and Prejudice'?",
                        "options": ["Jane Austen", "Emily Brontë", "Charlotte Brontë", "Virginia Woolf"],
                        "correct": "Jane Austen"
                    },
                    {
                        "question": "What is the capital of Canada?",
                        "options": ["Ottawa", "Toronto", "Montreal", "Vancouver"],
                        "correct": "Ottawa"
                    },
                    {
                        "question": "Who discovered electricity?",
                        "options": ["Thomas Edison", "Benjamin Franklin", "Albert Einstein", "Isaac Newton"],
                        "correct": "Benjamin Franklin"
                    },
                    {
                        "question": "What is the chemical symbol for gold?",
                        "options": ["Au", "Ag", "Pb", "Fe"],
                        "correct": "Au"
                    },
                    {
                        "question": "Who was the first man to step on the moon?",
                        "options": ["Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Michael Collins"],
                        "correct": "Neil Armstrong"
                    },
                    {
                        "question": "Which city is known as the 'Eternal City'?",
                        "options": ["Rome", "Athens", "Jerusalem", "Cairo"],
                        "correct": "Rome"
                    },
                    {
                        "question": "Who invented the telephone?",
                        "options": ["Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Guglielmo Marconi"],
                        "correct": "Alexander Graham Bell"
                    },
                    {
                        "question": "What is the chemical symbol for water?",
                        "options": ["H2O", "CO2", "CH4", "O2"],
                        "correct": "H2O"
                    },
                    {
                        "question": "Who wrote 'Romeo and Juliet'?",
                        "options": ["William Shakespeare", "Jane Austen", "Charles Dickens", "Emily Brontë"],
                        "correct": "William Shakespeare"
                    },
                    {
                        "question": "What is the world's largest ocean?",
                        "options": ["Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"],
                        "correct": "Pacific Ocean"
                    },
                    {
                        "question": "Which country is the largest by land area?",
                        "options": ["Russia", "Canada", "China", "United States"],
                        "correct": "Russia"
                    },
                    {
                        "question": "Who painted 'The Starry Night'?",
                        "options": ["Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"],
                        "correct": "Vincent van Gogh"
                    },
                    {
                        "question": "What is the chemical symbol for oxygen?",
                        "options": ["O", "O2", "H2O", "CO2"],
                        "correct": "O"
                    },
                    {
                        "question": "Which planet is known as the 'Blue Planet'?",
                        "options": ["Earth", "Mars", "Venus", "Neptune"],
                        "correct": "Earth"
                    },
                    {
                        "question": "Who composed 'The Four Seasons'?",
                        "options": ["Antonio Vivaldi", "Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Johann Sebastian Bach"],
                        "correct": "Antonio Vivaldi"
                    },
                    {
                        "question": "What is the capital of Spain?",
                        "options": ["Madrid", "Barcelona", "Seville", "Valencia"],
                        "correct": "Madrid"
                    },
                    {
                        "question": "Who discovered gravity?",
                        "options": ["Isaac Newton", "Galileo Galilei", "Albert Einstein", "Stephen Hawking"],
                        "correct": "Isaac Newton"
                    },
                    {
                        "question": "What is the chemical symbol for carbon?",
                        "options": ["C", "Ca", "Co", "Cu"],
                        "correct": "C"
                    },
                    {
                        "question": "Which country is known as the 'Land of the Midnight Sun'?",
                        "options": ["Norway", "Finland", "Sweden", "Iceland"],
                        "correct": "Norway"
                    },
                    {
                        "question": "Who wrote 'The Catcher in the Rye'?",
                        "options": ["J.D. Salinger", "F. Scott Fitzgerald", "Ernest Hemingway", "Mark Twain"],
                        "correct": "J.D. Salinger"
                    },
                    {
                        "question": "What is the chemical symbol for silver?",
                        "options": ["Ag", "Au", "Pt", "Fe"],
                        "correct": "Ag"
                    }
                    ]
                },



                {
                    "id":"2",
                    "title":"Python",
                    "subtitle":"Python concepts, Libraries",
                    "time":"10",
                    "questionList":[
                    {
                        "question":"Who developed python language?",
                        "options":["Wick Van Rossum","rasmus Lerdorf","Guido Van Rossum","Niene Stom"],
                        "correct":"Guido Van Rossum"
                    },
                    {
                        "question": "Which of the following is NOT a valid data type in Python?",
                        "options": ["int", "float", "char", "str"],
                        "correct": "char"
                    },
                    {
                        "question": "What is the output of 'print(2 + 3 * 5)' in Python?",
                        "options": ["10", "25", "17", "Error"],
                        "correct": "17"
                    },
                    {
                        "question": "What is the result of the expression '3 ** 2' in Python?",
                        "options": ["6", "9", "27", "Error"],
                        "correct": "9"
                    },
                    {
                        "question": "What does the 'pass' statement do in Python?",
                        "options": ["Skips execution and continues to the next statement", "Signals an error", "Terminates the program", "Does nothing"],
                        "correct": "Does nothing"
                    },
                    {
                        "question": "What is the correct way to declare a tuple in Python?",
                        "options": ["(1, 2, 3)", "[1, 2, 3]", "{1, 2, 3}", "<1, 2, 3>"],
                        "correct": "(1, 2, 3)"
                    },
                    {
                        "question": "What is the output of 'print('hello' + 'world')' in Python?",
                        "options": ["'helloworld'", "hello world", "Error", "None"],
                        "correct": "'helloworld'"
                    },
                    {
                        "question": "Which of the following is an example of a mutable data type in Python?",
                        "options": ["int", "str", "tuple", "list"],
                        "correct": "list"
                    },
                    {
                        "question": "What is the output of 'print(len('hello'))' in Python?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the result of '2 == 2.0' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "True"
                    },
                    {
                        "question": "What is the correct way to open a file named 'data.txt' for reading in Python?",
                        "options": ["file = open('data.txt', 'r')", "file = open('data.txt', 'w')", "file = open('data.txt', 'a')", "file = open('data.txt', 'rb')"],
                        "correct": "file = open('data.txt', 'r')"
                    },
                    {
                        "question": "What is the output of 'print('hello' * 3)' in Python?",
                        "options": ["'hellohellohello'", "hello hello hello", "Error", "None"],
                        "correct": "'hellohellohello'"
                    },
                    {
                        "question": "What is the correct way to create a function in Python?",
                        "options": ["def my_function():", "function my_function():", "func my_function():", "define my_function():"],
                        "correct": "def my_function():"
                    },
                    {
                        "question": "What is the result of '5 / 2' in Python?",
                        "options": ["2.5", "2", "2.0", "Error"],
                        "correct": "2.5"
                    },
                    {
                        "question": "What will be the output of the following code snippet?\ndef my_function(x, y=3):\n    return x + y\nprint(my_function(2))",
                        "options": ["2", "3", "5", "Error"],
                        "correct": "5"
                    },
                    {
                        "question": "What does the 'break' statement do in Python?",
                        "options": ["Exits the current loop", "Continues to the next iteration of the loop", "Raises an exception", "Terminates the program"],
                        "correct": "Exits the current loop"
                    },
                    {
                        "question": "What is the result of 'type('hello')' in Python?",
                        "options": ["int", "float", "str", "bool"],
                        "correct": "str"
                    },
                    {
                        "question": "What is the correct way to check if a key exists in a dictionary in Python?",
                        "options": ["if key in dict:", "if dict[key]:", "if dict.has_key(key):", "if key.exists(dict):"],
                        "correct": "if key in dict:"
                    },
                    {
                        "question": "What is the output of 'print(list(range(5)))' in Python?",
                        "options": ["[0, 1, 2, 3, 4]", "[1, 2, 3, 4, 5]", "[0, 1, 2, 3, 4, 5]", "[0, 1, 2, 3, 4, 5, 6]"],
                        "correct": "[0, 1, 2, 3, 4]"
                    },
                    {
                        "question": "What is the correct way to access the value of the key 'name' in the dictionary {'name': 'John', 'age': 30}?",
                        "options": ["dict['name']", "dict.get('name')", "dict.name", "dict[0]"],
                        "correct": "dict['name']"
                    },
                    {
                        "question": "What is the output of 'print('hello'.upper())' in Python?",
                        "options": ["hello", "HELLO", "Hello", "None"],
                        "correct": "HELLO"
                    },
                    {
                        "question": "What is the result of '10 % 3' in Python?",
                        "options": ["3", "1", "0", "Error"],
                        "correct": "1"
                    },
                    {
                        "question": "What is the correct way to import a module named 'my_module' in Python?",
                        "options": ["import my_module", "import module my_module", "from my_module import *", "include my_module"],
                        "correct": "import my_module"
                    },
                    {
                        "question": "What is the output of 'print('apple' in ['apple', 'banana', 'cherry'])' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "True"
                    },
                    {
                        "question": "What will be the result of the following code snippet?\n\nx = 10\ny = x\nx += 5\nprint(y)",
                        "options": ["10", "15", "5", "Error"],
                        "correct": "10"
                    },
                    {
                        "question": "What is the output of 'print('hello'.replace('l', 'w'))' in Python?",
                        "options": ["helwo", "hewlo", "hewwo", "None"],
                        "correct": "hewwo"
                    },
                    {
                        "question": "What does the 'continue' statement do in Python?",
                        "options": ["Skips the current iteration of the loop", "Exits the current loop", "Raises an exception", "Terminates the program"],
                        "correct": "Skips the current iteration of the loop"
                    },
                    {
                        "question": "What is the correct way to create a list in Python?",
                        "options": ["[1, 2, 3]", "(1, 2, 3)", "{1, 2, 3}", "<1, 2, 3>"],
                        "correct": "[1, 2, 3]"
                    },
                    {
                        "question": "What is the output of 'print('hello'.capitalize())' in Python?",
                        "options": ["hello", "Hello", "HELLO", "None"],
                        "correct": "Hello"
                    },
                    {
                        "question": "What is the result of '3 < 5 and 10 > 8' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "True"
                    },
                    {
                        "question": "What is the correct way to concatenate two lists in Python?",
                        "options": ["list1.extend(list2)", "list1.add(list2)", "list1.concat(list2)", "list1.append(list2)"],
                        "correct": "list1.extend(list2)"
                    },
                    {
                        "question": "What is the output of 'print(5 == '5')' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "False"
                    },
                    {
                        "question": "What is the result of 'len('hello')' in Python?",
                        "options": ["4", "5", "6", "Error"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the correct way to iterate over a list in Python?",
                        "options": ["for i in range(len(my_list)):", "for element in my_list:", "for i in range(my_list.length):", "for i from 0 to my_list.size:"],
                        "correct": "for element in my_list:"
                    },
                    {
                        "question": "What is the output of 'print(bool('False'))' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "True"
                    },
                    {
                        "question": "What is the result of 'not(3 > 5)' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "True"
                    },
                    {
                        "question": "What is the correct way to access the first element of a list named 'my_list' in Python?",
                        "options": ["my_list[0]", "my_list.first()", "my_list.get(0)", "my_list.get_first()"],
                        "correct": "my_list[0]"
                    },
                    {
                        "question": "What is the output of 'print('hello'.islower())' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "True"
                    },
                    {
                        "question": "What is the result of '5 == 5.0' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "True"
                    },
                    {
                        "question": "What is the correct way to remove an item from a list in Python?",
                        "options": ["my_list.remove(item)", "my_list.pop()", "my_list.delete(item)", "my_list.clear()"],
                        "correct": "my_list.remove(item)"
                    },
                    {
                        "question": "What is the output of 'print('hello'.startswith('H'))' in Python?",
                        "options": ["True", "False", "Error", "None"],
                        "correct": "False"
                    },
                    {
                        "question": "What is the result of '10 / 2' in Python?",
                        "options": ["5.0", "5", "2.0", "Error"],
                        "correct": "5.0"
                    },
                    {
                        "question": "What is the correct way to check if a key exists in a dictionary in Python?",
                        "options": ["if key in dict:", "if dict[key]:", "if dict.has_key(key):", "if key.exists(dict):"],
                        "correct": "if key in dict:"
                    }
                    ]
                },




                {
                    "id":"3",
                    "title":"Data science",
                    "subtitle":"Data Analysis,Visualization",
                    "time":"10",
                    "questionList":
                    [
                        {
                            "question": "What is the process of converting data into a more structured form called?",
                            "options": ["Data cleaning", "Data visualization", "Data preprocessing", "Data analysis"],
                            "correct": "Data preprocessing"
                        },
                        {
                            "question": "Which programming language is commonly used in data science for data analysis and manipulation?",
                            "options": ["Java", "C++", "Python", "Ruby"],
                            "correct": "Python"
                        },
                        {
                            "question": "What is the term for finding patterns or relationships in data?",
                            "options": ["Data modeling", "Data preprocessing", "Data mining", "Data visualization"],
                            "correct": "Data mining"
                        },
                        {
                            "question": "Which of the following is NOT a type of data visualization?",
                            "options": ["Histogram", "Bar chart", "Pie chart", "Dataframe"],
                            "correct": "Dataframe"
                        },
                        {
                            "question": "What is the correct term for a collection of data organized in rows and columns?",
                            "options": ["Table", "Matrix", "Array", "List"],
                            "correct": "Table"
                        },
                        {
                            "question": "What is the primary goal of exploratory data analysis (EDA)?",
                            "options": ["To summarize the main characteristics of the data", "To predict future outcomes", "To clean and preprocess the data", "To visualize relationships between variables"],
                            "correct": "To summarize the main characteristics of the data"
                        },
                        {
                            "question": "Which statistical measure is used to describe the spread of data?",
                            "options": ["Mean", "Median", "Mode", "Standard deviation"],
                            "correct": "Standard deviation"
                        },
                        {
                            "question": "What is the process of converting categorical variables into numerical format called?",
                            "options": ["One-hot encoding", "Normalization", "Standardization", "Feature scaling"],
                            "correct": "One-hot encoding"
                        },
                        {
                            "question": "Which machine learning algorithm is commonly used for classification tasks?",
                            "options": ["Linear regression", "Decision tree", "K-means clustering", "Principal component analysis"],
                            "correct": "Decision tree"
                        },
                        {
                            "question": "What is the purpose of cross-validation in machine learning?",
                            "options": ["To train the model on the entire dataset", "To evaluate the model's performance on unseen data", "To prevent overfitting", "To visualize the decision boundaries"],
                            "correct": "To evaluate the model's performance on unseen data"
                        },
                        {
                            "question": "Which library in Python is commonly used for data manipulation and analysis?",
                            "options": ["Matplotlib", "Seaborn", "Pandas", "Scikit-learn"],
                            "correct": "Pandas"
                        },
                        {
                            "question": "What is the correct term for data that contains outliers?",
                            "options": ["Skewed data", "Sparse data", "Noisy data", "Clean data"],
                            "correct": "Noisy data"
                        },
                        {
                            "question": "Which type of machine learning algorithm is used for making predictions based on past data?",
                            "options": ["Supervised learning", "Unsupervised learning", "Reinforcement learning", "Semi-supervised learning"],
                            "correct": "Supervised learning"
                        },
                        {
                            "question": "What is the primary goal of feature engineering?",
                            "options": ["To create new features from existing ones", "To remove irrelevant features", "To visualize the data", "To scale the features"],
                            "correct": "To create new features from existing ones"
                        },
                        {
                            "question": "What is the correct term for data that has missing values?",
                            "options": ["Sparse data", "Noisy data", "Clean data", "Missing data"],
                            "correct": "Missing data"
                        },
                        {
                            "question": "Which statistical test is used to determine the correlation between two continuous variables?",
                            "options": ["Chi-square test", "T-test", "Pearson correlation coefficient", "ANOVA"],
                            "correct": "Pearson correlation coefficient"
                        },
                        {
                            "question": "What is the correct term for a data point that lies far away from other observations?",
                            "options": ["Outlier", "Anomaly", "Extreme value", "Noise"],
                            "correct": "Outlier"
                        },
                        {
                            "question": "Which of the following is NOT a dimensionality reduction technique?",
                            "options": ["Principal component analysis (PCA)", "Singular value decomposition (SVD)", "K-nearest neighbors (KNN)", "t-distributed stochastic neighbor embedding (t-SNE)"],
                            "correct": "K-nearest neighbors (KNN)"
                        },
                        {
                            "question": "Which method is used to evaluate the performance of a classification model?",
                            "options": ["Accuracy", "Mean squared error", "R-squared", "Silhouette score"],
                            "correct": "Accuracy"
                        },
                        {
                            "question": "What is the correct term for a statistical model used to predict the outcome of a categorical variable?",
                            "options": ["Classifier", "Regressor", "Clustering algorithm", "Dimensionality reduction"],
                            "correct": "Classifier"
                        },
                        {
                            "question": "What is the purpose of feature scaling in machine learning?",
                            "options": ["To convert categorical variables into numerical format", "To normalize the features to a similar scale", "To remove irrelevant features", "To visualize the data distribution"],
                            "correct": "To normalize the features to a similar scale"
                        },
                        {
                            "question": "Which method is used to handle imbalanced classes in classification tasks?",
                            "options": ["Oversampling", "Undersampling", "SMOTE", "All of the above"],
                            "correct": "All of the above"
                        },
                        {
                            "question": "What is the output of 'print(df.head())' in Python, where 'df' is a DataFrame?",
                            "options": ["First 5 rows of the DataFrame", "Last 5 rows of the DataFrame", "Summary statistics of the DataFrame", "None"],
                            "correct": "First 5 rows of the DataFrame"
                        },
                        {
                            "question": "Which of the following is NOT a step in the data science process?",
                            "options": ["Data visualization", "Data preprocessing", "Data storage", "Model evaluation"],
                            "correct": "Data storage"
                        },
                        {
                            "question": "What is the purpose of grid search in machine learning?",
                            "options": ["To train the model on multiple subsets of the data", "To evaluate the model's performance on unseen data", "To tune hyperparameters using cross-validation", "To visualize the decision boundaries"],
                            "correct": "To tune hyperparameters using cross-validation"
                        },
                        {
                            "question": "What is the output of 'print(df.describe())' in Python, where 'df' is a DataFrame?",
                            "options": ["Summary statistics of the DataFrame", "First 5 rows of the DataFrame", "Last 5 rows of the DataFrame", "None"],
                            "correct": "Summary statistics of the DataFrame"
                        },
                        {
                            "question": "What is the correct term for a statistical model used to predict the outcome of a continuous variable?",
                            "options": ["Classifier", "Regressor", "Clustering algorithm", "Dimensionality reduction"],
                            "correct": "Regressor"
                        },
                        {
                            "question": "What is the purpose of k-fold cross-validation?",
                            "options": ["To train the model on multiple subsets of the data", "To evaluate the model's performance on unseen data", "To tune hyperparameters using cross-validation", "To visualize the decision boundaries"],
                            "correct": "To train the model on multiple subsets of the data"
                        },
                        {
                            "question": "Which method is used to handle missing values in a dataset?",
                            "options": ["Imputation", "Normalization", "Standardization", "Feature scaling"],
                            "correct": "Imputation"
                        },
                        {
                            "question": "What is the primary goal of feature selection?",
                            "options": ["To create new features from existing ones", "To remove irrelevant features", "To visualize the data", "To scale the features"],
                            "correct": "To remove irrelevant features"
                        },
                        {
                            "question": "What is the correct term for a dataset that contains a target variable?",
                            "options": ["Feature matrix", "Input data", "Output data", "Labelled data"],
                            "correct": "Labelled data"
                        },
                        {
                            "question": "Which method is used to evaluate the performance of a regression model?",
                            "options": ["Mean squared error", "Accuracy", "F1 score", "Silhouette score"],
                            "correct": "Mean squared error"
                        },
                        {
                            "question": "What is the purpose of principal component analysis (PCA) in dimensionality reduction?",
                            "options": ["To identify clusters in the data", "To transform the features into a lower-dimensional space", "To select the most important features", "To normalize the features to a similar scale"],
                            "correct": "To transform the features into a lower-dimensional space"
                        },
                        {
                            "question": "Which of the following is NOT a type of supervised learning algorithm?",
                            "options": ["Linear regression", "K-means clustering", "Logistic regression", "Decision tree"],
                            "correct": "K-means clustering"
                        },
                        {
                            "question": "What is the purpose of outlier detection in data preprocessing?",
                            "options": ["To remove irrelevant features", "To identify abnormal data points", "To visualize the data distribution", "To normalize the features to a similar scale"],
                            "correct": "To identify abnormal data points"
                        },
                        {
                            "question": "Which method is used to handle class imbalance in classification tasks?",
                            "options": ["Overfitting", "Undersampling", "Oversampling", "Feature selection"],
                            "correct": "Oversampling"
                        },
                        {
                            "question": "What is the correct term for a measure of the association between two categorical variables?",
                            "options": ["Chi-square test", "T-test", "Pearson correlation coefficient", "ANOVA"],
                            "correct": "Chi-square test"
                        },
                        {
                            "question": "What is the purpose of a confusion matrix in machine learning?",
                            "options": ["To visualize the decision boundaries", "To evaluate the model's performance on unseen data", "To summarize the performance of a classification model", "To select the most important features"],
                            "correct": "To summarize the performance of a classification model"
                        },
                        {
                            "question": "What is the output of 'print(df.info())' in Python, where 'df' is a DataFrame?",
                            "options": ["Summary information about the DataFrame", "First 5 rows of the DataFrame", "Last 5 rows of the DataFrame", "None"],
                            "correct": "Summary information about the DataFrame"
                        },
                        {
                            "question": "What is the correct term for a dataset that contains only input variables?",
                            "options": ["Feature matrix", "Input data", "Output data", "Labelled data"],
                            "correct": "Feature matrix"
                        },
                        {
                            "question": "Which method is used to handle multicollinearity in regression analysis?",
                            "options": ["Feature scaling", "Feature selection", "Regularization", "Imputation"],
                            "correct": "Regularization"
                        },
                        {
                            "question": "What is the purpose of a ROC curve in machine learning?",
                            "options": ["To evaluate the model's performance on unseen data", "To summarize the performance of a classification model", "To select the most important features", "To visualize the decision boundaries"],
                            "correct": "To visualize the performance of a classification model"
                        }
                    ]
                },



                {

                    "id":"4",
                    "title":"Java",
                    "subtitle":"Oops concepts",
                    "time":"10",
                    "questionList":
                    [
                        {
                            "question": "What is the output of the following code snippet?\n\nint x = 5;\nSystem.out.println(x++);",
                            "options": ["5", "6", "Error", "None"],
                            "correct": "5"
                        },
                        {
                            "question": "Which keyword is used to define a subclass in Java?",
                            "options": ["this", "super", "extends", "implements"],
                            "correct": "extends"
                        },
                        {
                            "question": "What is the correct way to declare a constant variable in Java?",
                            "options": ["final int x = 10;", "const int x = 10;", "static final int x = 10;", "final static int x = 10;"],
                            "correct": "final static int x = 10;"
                        },
                        {
                            "question": "What is the purpose of the 'break' statement in Java?",
                            "options": ["To terminate the loop", "To skip the current iteration of the loop", "To exit the program", "To continue to the next iteration of the loop"],
                            "correct": "To terminate the loop"
                        },
                        {
                            "question": "Which of the following is NOT a primitive data type in Java?",
                            "options": ["int", "String", "boolean", "double"],
                            "correct": "String"
                        },
                        {
                            "question": "What is the correct syntax to instantiate an object in Java?",
                            "options": ["MyClass obj = new MyClass();", "MyClass obj = MyClass();", "new MyClass obj();", "MyClass obj();"],
                            "correct": "MyClass obj = new MyClass();"
                        },
                        {
                            "question": "What is the output of 'System.out.println(10 > 5 && 10 < 20)' in Java?",
                            "options": ["true", "false", "Error", "None"],
                            "correct": "true"
                        },
                        {
                            "question": "What is the correct way to declare a method in Java that does not return any value?",
                            "options": ["void myMethod() {}", "int myMethod() {}", "String myMethod() {}", "double myMethod() {}"],
                            "correct": "void myMethod() {}"
                        },
                        {
                            "question": "Which keyword is used to refer to the current instance of the class in Java?",
                            "options": ["this", "super", "extends", "implements"],
                            "correct": "this"
                        },
                        {
                            "question": "What is the purpose of the 'continue' statement in Java?",
                            "options": ["To terminate the loop", "To skip the current iteration of the loop", "To exit the program", "To continue to the next iteration of the loop"],
                            "correct": "To continue to the next iteration of the loop"
                        },
                        {
                            "question": "Which of the following is a valid identifier in Java?",
                            "options": ["1variable", "_variable", "variable$", "variable-name"],
                            "correct": "_variable"
                        },
                        {
                            "question": "What is the correct way to declare a variable in Java?",
                            "options": ["var x = 10;", "int x = 10;", "integer x = 10;", "const x = 10;"],
                            "correct": "int x = 10;"
                        },
                        {
                            "question": "What is the purpose of the 'static' keyword in Java?",
                            "options": ["To specify that a variable or method belongs to the class, not to instances of the class", "To prevent the inheritance of a class", "To declare a constant variable", "To specify that a variable or method belongs to instances of the class, not to the class itself"],
                            "correct": "To specify that a variable or method belongs to the class, not to instances of the class"
                        },
                        {
                            "question": "What is the output of 'System.out.println(\"Hello\".charAt(0))' in Java?",
                            "options": ["Hello", "H", "e", "Error"],
                            "correct": "H"
                        },
                        {
                            "question": "What is the result of '10 / 3' in Java?",
                            "options": ["3", "3.333", "3.0", "Error"],
                            "correct": "3"
                        },
                        {
                            "question": "What is the correct way to declare a constructor in Java?",
                            "options": ["void MyClass() {}", "MyClass() {}", "constructor MyClass() {}", "MyClass constructor() {}"],
                            "correct": "MyClass() {}"
                        },
                        {
                            "question": "What is the output of 'System.out.println(Math.pow(2, 3))' in Java?",
                            "options": ["8", "6", "2^3", "Error"],
                            "correct": "8"
                        },
                        {
                            "question": "What is the purpose of the 'final' keyword in Java?",
                            "options": ["To prevent a method from being overridden", "To prevent a class from being inherited", "To declare a constant variable", "To specify that a variable or method belongs to the class, not to instances of the class"],
                            "correct": "To declare a constant variable"
                        },
                        {
                            "question": "What is the correct way to declare an array in Java?",
                            "options": ["int[] arr = new int[];", "int arr[] = new int[];", "int arr[5];", "int arr[] = new int[5];"],
                            "correct": "int arr[] = new int[5];"
                        },
                        {
                            "question": "What is the output of 'System.out.println(\"Hello\".substring(0, 3))' in Java?",
                            "options": ["Hello", "Hell", "Hel", "Error"],
                            "correct": "Hel"
                        },
                        {
                            "question": "What is the result of '5 == 5.0' in Java?",
                            "options": ["true", "false", "Error", "None"],
                            "correct": "true"
                        },
                        {
                            "question": "What is the correct way to declare a method that can be accessed without creating an instance of the class?",
                            "options": ["public void myMethod() {}", "private void myMethod() {}", "static void myMethod() {}", "final void myMethod() {}"],
                            "correct": "static void myMethod() {}"
                        },
                        {
                            "question": "What is the purpose of the 'super' keyword in Java?",
                            "options": ["To refer to the current instance of the class", "To call the constructor of the superclass", "To prevent the inheritance of a class", "To specify that a variable or method belongs to the class, not to instances of the class"],
                            "correct": "To call the constructor of the superclass"
                        },
                        {
                            "question": "What is the output of 'System.out.println(10 + \"5\")' in Java?",
                            "options": ["105", "15", "Error", "None"],
                            "correct": "105"
                        },
                        {
                            "question": "What is the correct way to declare a method that returns an integer value in Java?",
                            "options": ["void myMethod() {}", "int myMethod() {}", "String myMethod() {}", "double myMethod() {}"],
                            "correct": "int myMethod() {}"
                        },
                        {
                            "question": "Which of the following is NOT a valid Java comment?",
                            "options": ["// This is a comment", "/* This is a comment */", "# This is a comment", "// This is also a comment"],
                            "correct": "# This is a comment"
                        },
                        {
                            "question": "What is the purpose of the 'new' keyword in Java?",
                            "options": ["To declare a constant variable", "To create a new instance of a class", "To specify that a variable or method belongs to the class, not to instances of the class", "To prevent the inheritance of a class"],
                            "correct": "To create a new instance of a class"
                        },
                        {
                            "question": "What is the output of 'System.out.println(\"Hello\".length())' in Java?",
                            "options": ["Hello", "5", "Error", "None"],
                            "correct": "5"
                        },
                        {
                            "question": "What is the result of '10 % 3' in Java?",
                            "options": ["3", "1", "0", "Error"],
                            "correct": "1"
                        },
                        {
                            "question": "What is the correct way to declare a method that can only be accessed within the same class?",
                            "options": ["public void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                            "correct": "private void myMethod() {}"
                        },
                        {
                            "question": "What is the output of 'System.out.println(\"Hello\" + \"World\")' in Java?",
                            "options": ["HelloWorld", "Hello World", "Hello+World", "Error"],
                            "correct": "HelloWorld"
                        },
                        {
                            "question": "What is the purpose of the 'void' keyword in Java?",
                            "options": ["To specify that a method does not return any value", "To prevent the inheritance of a class", "To declare a constant variable", "To specify that a variable or method belongs to the class, not to instances of the class"],
                            "correct": "To specify that a method does not return any value"
                        },
                        {
                            "question": "What is the correct way to declare a method that can be accessed by subclasses?",
                            "options": ["public void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                            "correct": "protected void myMethod() {}"
                        },
                        {
                            "question": "What is the output of 'System.out.println(\"Hello\".toUpperCase())' in Java?",
                            "options": ["Hello", "HELLO", "Error", "None"],
                            "correct": "HELLO"
                        },
                        {
                            "question": "What is the purpose of the 'public' keyword in Java?",
                            "options": ["To specify that a method does not return any value", "To prevent the inheritance of a class", "To declare a constant variable", "To specify that a variable or method belongs to the class, not to instances of the class"],
                            "correct": "To specify that a variable or method belongs to the class, not to instances of the class"
                        },
                        {
                            "question": "What is the correct way to declare a method that cannot be overridden by subclasses?",
                            "options": ["final void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                            "correct": "final void myMethod() {}"
                        },
                        {
                            "question": "What is the output of 'System.out.println(10 == 5 || 10 < 20)' in Java?",
                            "options": ["true", "false", "Error", "None"],
                            "correct": "true"
                        },
                        {
                            "question": "What is the correct way to declare a method that returns a string value in Java?",
                            "options": ["void myMethod() {}", "int myMethod() {}", "String myMethod() {}", "double myMethod() {}"],
                            "correct": "String myMethod() {}"
                        }
                    ]
                },



                {
                    "id":"5",
                    "title":"Aptitude",
                    "subtitle":"Arithmetic, Algebra, Geometry",
                    "time":"10",
                    "questionList":[
                    {
                        "question": "What is the value of 2 + 2 * 3?",
                        "options": ["6", "8", "10", "12"],
                        "correct": "8"
                    },
                    {
                        "question": "If a train travels at a speed of 50 km/h, how far will it travel in 2.5 hours?",
                        "options": ["125 km", "150 km", "175 km", "200 km"],
                        "correct": "125 km"
                    },
                    {
                        "question": "If the cost of 5 pens is $15, what is the cost of 12 pens?",
                        "options": ["$36", "$30", "$27", "$20"],
                        "correct": "$36"
                    },
                    {
                        "question": "A car travels a distance of 240 km at a constant speed. If it takes 4 hours to cover the distance, what is its speed?",
                        "options": ["50 km/h", "60 km/h", "70 km/h", "80 km/h"],
                        "correct": "60 km/h"
                    },
                    {
                        "question": "If the selling price of an item is $100 and the profit earned is 25%, what is the cost price?",
                        "options": ["$75", "$80", "$90", "$110"],
                        "correct": "$80"
                    },
                    {
                        "question": "If the perimeter of a rectangle is 30 cm and its length is 10 cm, what is its width?",
                        "options": ["5 cm", "6 cm", "7 cm", "8 cm"],
                        "correct": "5 cm"
                    },
                    {
                        "question": "If a shopkeeper sells an item at a loss of 20% and the selling price is $80, what is the cost price of the item?",
                        "options": ["$90", "$100", "$110", "$120"],
                        "correct": "$100"
                    },
                    {
                        "question": "What is 30% of 400?",
                        "options": ["120", "150", "180", "200"],
                        "correct": "120"
                    },
                    {
                        "question": "If 6 workers can complete a task in 8 days, how many days will it take for 4 workers to complete the same task?",
                        "options": ["10 days", "12 days", "14 days", "16 days"],
                        "correct": "12 days"
                    },
                    {
                        "question": "What is the next number in the series: 2, 5, 10, 17, 26, ...?",
                        "options": ["36", "37", "38", "39"],
                        "correct": "37"
                    },
                    {
                        "question": "If the area of a square is 144 square meters, what is its side length?",
                        "options": ["12 meters", "14 meters", "16 meters", "18 meters"],
                        "correct": "12 meters"
                    },
                    {
                        "question": "If x = 5 and y = 3, what is the value of x^2 + y^2?",
                        "options": ["19", "24", "28", "34"],
                        "correct": "34"
                    },
                    {
                        "question": "A bike travels a distance of 120 km at a speed of 40 km/h. How long does it take to cover the distance?",
                        "options": ["2 hours", "3 hours", "4 hours", "5 hours"],
                        "correct": "3 hours"
                    },
                    {
                        "question": "If the ratio of boys to girls in a class is 3:2 and there are 20 girls, how many boys are there?",
                        "options": ["20 boys", "30 boys", "40 boys", "50 boys"],
                        "correct": "30 boys"
                    },
                    {
                        "question": "What is the sum of the first 10 natural numbers?",
                        "options": ["45", "50", "55", "60"],
                        "correct": "55"
                    },
                    {
                        "question": "If the population of a city increases by 5% each year, what is the population after 3 years if the current population is 100,000?",
                        "options": ["115,762", "115,763", "115,764", "115,765"],
                        "correct": "115,762"
                    },
                    {
                        "question": "If 20% of a number is 40, what is the number?",
                        "options": ["150", "160", "170", "180"],
                        "correct": "200"
                    },
                    {
                        "question": "If the area of a circle is 154 square meters, what is its radius?",
                        "options": ["7 meters", "8 meters", "9 meters", "10 meters"],
                        "correct": "7 meters"
                    },
                    {
                        "question": "What is the next number in the series: 1, 4, 9, 16, 25, ...?",
                        "options": ["36", "49", "64", "81"],
                        "correct": "36"
                    },
                    {
                        "question": "If a car travels a distance of 360 km at a speed of 60 km/h, how long does it take to cover the distance?",
                        "options": ["4 hours", "5 hours", "6 hours", "7 hours"],
                        "correct": "6 hours"
                    },
                    {
                        "question": "What is 25% of 200?",
                        "options": ["40", "45", "50", "55"],
                        "correct": "50"
                    },
                    {
                        "question": "If 3x - 5 = 10, what is the value of x?",
                        "options": ["5", "6", "7", "8"],
                        "correct": "5"
                    },
                    {
                        "question": "If the average of 5 numbers is 20, what is their sum?",
                        "options": ["80", "90", "100", "110"],
                        "correct": "100"
                    },
                    {
                        "question": "What is the next number in the series: 3, 6, 12, 24, 48, ...?",
                        "options": ["96", "100", "102", "104"],
                        "correct": "96"
                    },
                    {
                        "question": "If 30% of a number is 45, what is the number?",
                        "options": ["150", "145", "140", "135"],
                        "correct": "150"
                    },
                    {
                        "question": "What is the value of 4^3 - 2^3?",
                        "options": ["60", "62", "64", "66"],
                        "correct": "60"
                    },
                    {
                        "question": "If the cost price of an item is $50 and the selling price is $75, what is the profit percentage?",
                        "options": ["25%", "30%", "35%", "40%"],
                        "correct": "50%"
                    },
                    {
                        "question": "What is the next number in the series: 2, 3, 5, 8, 12, ...?",
                        "options": ["15", "18", "20", "24"],
                        "correct": "18"
                    },
                    {
                        "question": "If the perimeter of a square is 48 cm, what is its side length?",
                        "options": ["10 cm", "12 cm", "14 cm", "16 cm"],
                        "correct": "12 cm"
                    },
                    {
                        "question": "What is 20% of 250?",
                        "options": ["40", "45", "50", "55"],
                        "correct": "50"
                    },
                    {
                        "question": "If 8x - 4 = 20, what is the value of x?",
                        "options": ["3", "4", "5", "6"],
                        "correct": "3"
                    },
                    {
                        "question": "If the volume of a cube is 64 cubic meters, what is its side length?",
                        "options": ["4 meters", "5 meters", "6 meters", "7 meters"],
                        "correct": "4 meters"
                    },
                    {
                        "question": "What is the sum of the first 20 natural numbers?",
                        "options": ["190", "200", "210", "220"],
                        "correct": "210"
                    },
                    {
                        "question": "If the selling price of an item is $200 and the profit percentage is 25%, what is the cost price?",
                        "options": ["$160", "$180", "$190", "$200"],
                        "correct": "$160"
                    },
                    {
                        "question": "What is the next number in the series: 1, 3, 6, 10, 15, ...?",
                        "options": ["21", "24", "28", "30"],
                        "correct": "21"
                    },
                    {
                        "question": "If a shopkeeper sells an item at a loss of 10% and the selling price is $90, what is the cost price of the item?",
                        "options": ["$80", "$85", "$90", "$95"],
                        "correct": "$100"
                    },
                    {
                        "question": "What is the next number in the series: 2, 4, 8, 16, 32, ...?",
                        "options": ["64", "128", "256", "512"],
                        "correct": "64"
                    },
                    {
                        "question": "If the area of a rectangle is 60 square meters and its length is 10 meters, what is its width?",
                        "options": ["4 meters", "5 meters", "6 meters", "7 meters"],
                        "correct": "6 meters"
                    },
                    {
                        "question": "What is the result of 10% of 80% of 200?",
                        "options": ["$12", "$16", "$20", "$24"],
                        "correct": "$16"
                    },
                    {
                        "question": "If 4x + 3 = 15, what is the value of x?",
                        "options": ["3", "4", "5", "6"],
                        "correct": "3"
                    }
                    ]
                },


                {
                    "id":"6",
                    "title":"C++",
                    "subtitle":"C++ syntax, Principles",
                    "time":"10",
                    "questionList":[
                    {
                        "question": "What is the output of the following code snippet?\n\n#include <iostream>\nusing namespace std;\n\nint main() {\n    int x = 5;\n    cout << x++ << endl;\n    return 0;\n}",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "Which of the following is NOT a valid data type in C++?",
                        "options": ["int", "real", "char", "double"],
                        "correct": "real"
                    },
                    {
                        "question": "What is the correct way to declare a constant variable in C++?",
                        "options": ["const int x = 10;", "constant int x = 10;", "static const int x = 10;", "final int x = 10;"],
                        "correct": "const int x = 10;"
                    },
                    {
                        "question": "What is the purpose of the 'break' statement in C++?",
                        "options": ["To terminate the loop", "To skip the current iteration of the loop", "To exit the program", "To continue to the next iteration of the loop"],
                        "correct": "To terminate the loop"
                    },
                    {
                        "question": "Which of the following is NOT a valid way to initialize a variable in C++?",
                        "options": ["int x = 5;", "int x(5);", "int x{5};", "int x = {5};"],
                        "correct": "int x = {5};"
                    },
                    {
                        "question": "What is the output of 'cout << (5 > 3 && 8 < 5)' in C++?",
                        "options": ["true", "false", "Error", "None"],
                        "correct": "false"
                    },
                    {
                        "question": "What is the correct syntax to declare a function in C++?",
                        "options": ["void myFunction() {}", "function myFunction() {}", "def myFunction() {}", "sub myFunction() {}"],
                        "correct": "void myFunction() {}"
                    },
                    {
                        "question": "What is the purpose of the 'continue' statement in C++?",
                        "options": ["To terminate the loop", "To skip the current iteration of the loop", "To exit the program", "To continue to the next iteration of the loop"],
                        "correct": "To continue to the next iteration of the loop"
                    },
                    {
                        "question": "What is the correct way to declare a 2D array in C++?",
                        "options": ["int arr[5][5];", "int arr[][];", "int arr[5,5];", "int arr[][] = new int[5][5];"],
                        "correct": "int arr[5][5];"
                    },
                    {
                        "question": "Which of the following is NOT a valid operator in C++?",
                        "options": ["&", "$", "|", "^"],
                        "correct": "$"
                    },
                    {
                        "question": "What is the output of 'cout << \"Hello\".length()' in C++?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the result of '10 / 3' in C++?",
                        "options": ["3", "3.333", "3.0", "Error"],
                        "correct": "3"
                    },
                    {
                        "question": "What is the correct way to declare a function that returns a value in C++?",
                        "options": ["void myFunction() {}", "int myFunction() {}", "string myFunction() {}", "double myFunction() {}"],
                        "correct": "int myFunction() {}"
                    },
                    {
                        "question": "What is the purpose of the 'static' keyword in C++?",
                        "options": ["To specify that a variable or method belongs to the class, not to instances of the class", "To prevent the inheritance of a class", "To declare a constant variable", "To specify that a variable or method belongs to instances of the class, not to the class itself"],
                        "correct": "To specify that a variable or method belongs to the class, not to instances of the class"
                    },
                    {
                        "question": "What is the output of 'cout << (10 == 5 || 10 < 20)' in C++?",
                        "options": ["true", "false", "Error", "None"],
                        "correct": "true"
                    },
                    {
                        "question": "What is the purpose of the 'this' keyword in C++?",
                        "options": ["To refer to the current instance of the class", "To call the constructor of the superclass", "To prevent the inheritance of a class", "To specify that a variable or method belongs to the class, not to instances of the class"],
                        "correct": "To refer to the current instance of the class"
                    },
                    {
                        "question": "What is the correct syntax to declare a class in C++?",
                        "options": ["class MyClass {}", "class MyClass();", "MyClass class {}", "MyClass {}"],
                        "correct": "class MyClass {}"
                    },
                    {
                        "question": "What is the output of 'cout << (10 + \"5\")' in C++?",
                        "options": ["105", "15", "Error", "None"],
                        "correct": "Error"
                    },
                    {
                        "question": "What is the purpose of the 'virtual' keyword in C++?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To prevent the inheritance of a class", "To declare a constant variable", "To allow dynamic polymorphism in class hierarchies"],
                        "correct": "To allow dynamic polymorphism in class hierarchies"
                    },
                    {
                        "question": "What is the correct way to declare a constructor in C++?",
                        "options": ["MyClass()", "void MyClass()", "constructor MyClass()", "MyClass constructor()"],
                        "correct": "MyClass()"
                    },
                    {
                        "question": "What is the output of 'cout << (10 / 3)' in C++?",
                        "options": ["3", "3.333", "3.0", "Error"],
                        "correct": "3"
                    },
                    {
                        "question": "What is the purpose of the 'new' keyword in C++?",
                        "options": ["To declare a constant variable", "To create a new instance of a class", "To prevent the inheritance of a class", "To specify that a variable or method belongs to the class, not to instances of the class"],
                        "correct": "To create a new instance of a class"
                    },
                    {
                        "question": "What is the correct syntax to define a function outside of a class in C++?",
                        "options": ["void myFunction() {}", "myFunction() {}", "define myFunction() {}", "function myFunction() {}"],
                        "correct": "void myFunction() {}"
                    },
                    {
                        "question": "What is the output of 'cout << (\"Hello\" + \"World\")' in C++?",
                        "options": ["HelloWorld", "Hello World", "Hello+World", "Error"],
                        "correct": "Error"
                    },
                    {
                        "question": "What is the purpose of the 'friend' keyword in C++?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To prevent the inheritance of a class", "To declare a constant variable", "To allow a function or class to access private members of a class"],
                        "correct": "To allow a function or class to access private members of a class"
                    },
                    {
                        "question": "What is the correct way to declare a method that can only be accessed within the same class in C++?",
                        "options": ["public void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                        "correct": "private void myMethod() {}"
                    },
                    {
                        "question": "What is the output of 'cout << (\"Hello\".length())' in C++?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "Error"
                    },
                    {
                        "question": "What is the purpose of the 'protected' keyword in C++?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To allow a function or class to access private members of a class", "To prevent the inheritance of a class", "To allow access to derived classes and friend functions"],
                        "correct": "To allow access to derived classes and friend functions"
                    },
                    {
                        "question": "What is the correct way to declare a method that can be accessed without creating an instance of the class in C++?",
                        "options": ["public void myMethod() {}", "private void myMethod() {}", "static void myMethod() {}", "final void myMethod() {}"],
                        "correct": "static void myMethod() {}"
                    },
                    {
                        "question": "What is the output of 'cout << (10 == 5 && 10 < 20)' in C++?",
                        "options": ["true", "false", "Error", "None"],
                        "correct": "false"
                    },
                    {
                        "question": "What is the purpose of the 'protected' keyword in C++?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To allow a function or class to access private members of a class", "To prevent the inheritance of a class", "To allow access to derived classes and friend functions"],
                        "correct": "To allow access to derived classes and friend functions"
                    },
                    {
                        "question": "What is the correct syntax to declare a method that returns a string value in C++?",
                        "options": ["void myMethod() {}", "int myMethod() {}", "string myMethod() {}", "double myMethod() {}"],
                        "correct": "string myMethod() {}"
                    },
                    {
                        "question": "What is the purpose of the 'public' keyword in C++?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To allow access to derived classes and friend functions", "To prevent the inheritance of a class", "To specify that a variable or method belongs to the class, not to instances of the class"],
                        "correct": "To specify that a variable or method belongs to instances of the class, not to the class itself"
                    },
                    {
                        "question": "What is the correct way to declare a method that cannot be overridden by subclasses in C++?",
                        "options": ["final void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                        "correct": "final void myMethod() {}"
                    },
                    {
                        "question": "What is the output of 'cout << (\"Hello\".size())' in C++?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the purpose of the 'void' keyword in C++?",
                        "options": ["To specify that a method does not return any value", "To allow access to derived classes and friend functions", "To prevent the inheritance of a class", "To specify that a variable or method belongs to instances of the class, not to the class itself"],
                        "correct": "To specify that a method does not return any value"
                    },
                    {
                        "question": "What is the correct way to declare a method that can be accessed by subclasses in C++?",
                        "options": ["public void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                        "correct": "protected void myMethod() {}"
                    },
                    {
                        "question": "What is the output of 'cout << (\"Hello\".toupper())' in C++?",
                        "options": ["Hello", "HELLO", "Error", "None"],
                        "correct": "Error"
                    }
                    ]
                },



                {
                    "id":"7",
                    "title":"C Language",
                    "subtitle":"C basics, Data types, Pointers",
                    "time":"10",
                    "questionList":[
                    {
                        "question": "What is the output of the following code snippet?\n\n#include <stdio.h>\n\nint main() {\n    int x = 5;\n    printf(\"%d\\n\", x++);\n    return 0;\n}",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "Which of the following is NOT a valid data type in C?",
                        "options": ["int", "real", "char", "float"],
                        "correct": "real"
                    },
                    {
                        "question": "What is the correct way to declare a constant variable in C?",
                        "options": ["const int x = 10;", "constant int x = 10;", "static const int x = 10;", "final int x = 10;"],
                        "correct": "const int x = 10;"
                    },
                    {
                        "question": "What is the purpose of the 'break' statement in C?",
                        "options": ["To terminate the loop", "To skip the current iteration of the loop", "To exit the program", "To continue to the next iteration of the loop"],
                        "correct": "To terminate the loop"
                    },
                    {
                        "question": "Which of the following is NOT a valid way to initialize a variable in C?",
                        "options": ["int x = 5;", "int x(5);", "int x{5};", "int x = {5};"],
                        "correct": "int x = {5};"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", 5 > 3 && 8 < 5);' in C?",
                        "options": ["1", "0", "Error", "None"],
                        "correct": "0"
                    },
                    {
                        "question": "What is the correct syntax to declare a function in C?",
                        "options": ["void myFunction() {}", "function myFunction() {}", "def myFunction() {}", "sub myFunction() {}"],
                        "correct": "void myFunction() {}"
                    },
                    {
                        "question": "What is the purpose of the 'continue' statement in C?",
                        "options": ["To terminate the loop", "To skip the current iteration of the loop", "To exit the program", "To continue to the next iteration of the loop"],
                        "correct": "To continue to the next iteration of the loop"
                    },
                    {
                        "question": "What is the correct way to declare a 2D array in C?",
                        "options": ["int arr[5][5];", "int arr[][];", "int arr[5,5];", "int arr[][] = new int[5][5];"],
                        "correct": "int arr[5][5];"
                    },
                    {
                        "question": "Which of the following is NOT a valid operator in C?",
                        "options": ["&", "$", "|", "^"],
                        "correct": "$"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", strlen(\"Hello\"));' in C?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the result of '10 / 3' in C?",
                        "options": ["3", "3.333", "3.0", "Error"],
                        "correct": "3"
                    },
                    {
                        "question": "What is the correct way to declare a function that returns a value in C?",
                        "options": ["void myFunction() {}", "int myFunction() {}", "string myFunction() {}", "double myFunction() {}"],
                        "correct": "int myFunction() {}"
                    },
                    {
                        "question": "What is the purpose of the 'static' keyword in C?",
                        "options": ["To specify that a variable or method belongs to the class, not to instances of the class", "To prevent the inheritance of a class", "To declare a constant variable", "To specify that a variable or method belongs to instances of the class, not to the class itself"],
                        "correct": "To specify that a variable or method belongs to the class, not to instances of the class"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", 10 == 5 || 10 < 20);' in C?",
                        "options": ["1", "0", "Error", "None"],
                        "correct": "1"
                    },
                    {
                        "question": "What is the purpose of the 'this' keyword in C?",
                        "options": ["To refer to the current instance of the class", "To call the constructor of the superclass", "To prevent the inheritance of a class", "To specify that a variable or method belongs to the class, not to instances of the class"],
                        "correct": "To refer to the current instance of the class"
                    },
                    {
                        "question": "What is the correct syntax to declare a structure in C?",
                        "options": ["struct MyStruct {}", "struct MyStruct();", "MyStruct struct {}", "MyStruct {}"],
                        "correct": "struct MyStruct {}"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", 10 + \"5\");' in C?",
                        "options": ["105", "15", "Error", "None"],
                        "correct": "Error"
                    },
                    {
                        "question": "What is the purpose of the 'virtual' keyword in C?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To prevent the inheritance of a class", "To declare a constant variable", "To allow dynamic polymorphism in class hierarchies"],
                        "correct": "To allow dynamic polymorphism in class hierarchies"
                    },
                    {
                        "question": "What is the correct way to declare a constructor in C?",
                        "options": ["MyStruct()", "void MyStruct()", "constructor MyStruct()", "MyStruct constructor()"],
                        "correct": "MyStruct()"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", 10 / 3);' in C?",
                        "options": ["3", "3.333", "3.0", "Error"],
                        "correct": "3"
                    },
                    {
                        "question": "What is the purpose of the 'new' keyword in C?",
                        "options": ["To declare a constant variable", "To create a new instance of a class", "To prevent the inheritance of a class", "To specify that a variable or method belongs to the class, not to instances of the class"],
                        "correct": "To create a new instance of a class"
                    },
                    {
                        "question": "What is the correct syntax to define a function outside of a structure in C?",
                        "options": ["void myFunction() {}", "myFunction() {}", "define myFunction() {}", "function myFunction() {}"],
                        "correct": "void myFunction() {}"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", strlen(\"Hello\"));' in C?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the purpose of the 'friend' keyword in C?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To prevent the inheritance of a class", "To declare a constant variable", "To allow a function or class to access private members of a class"],
                        "correct": "To allow a function or class to access private members of a class"
                    },
                    {
                        "question": "What is the correct way to declare a function that can only be accessed within the same structure in C?",
                        "options": ["public void myFunction() {}", "private void myFunction() {}", "protected void myFunction() {}", "static void myFunction() {}"],
                        "correct": "private void myFunction() {}"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", strlen(\"Hello\"));' in C?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the purpose of the 'protected' keyword in C?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To allow a function or class to access private members of a class", "To prevent the inheritance of a class", "To allow access to derived classes and friend functions"],
                        "correct": "To allow access to derived classes and friend functions"
                    },
                    {
                        "question": "What is the correct way to declare a method that can be accessed without creating an instance of the structure in C?",
                        "options": ["public void myMethod() {}", "private void myMethod() {}", "static void myMethod() {}", "final void myMethod() {}"],
                        "correct": "static void myMethod() {}"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", 10 == 5 && 10 < 20);' in C?",
                        "options": ["1", "0", "Error", "None"],
                        "correct": "0"
                    },
                    {
                        "question": "What is the purpose of the 'protected' keyword in C?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To allow a function or class to access private members of a class", "To prevent the inheritance of a class", "To allow access to derived classes and friend functions"],
                        "correct": "To allow access to derived classes and friend functions"
                    },
                    {
                        "question": "What is the correct syntax to declare a method that returns a string value in C?",
                        "options": ["void myMethod() {}", "int myMethod() {}", "string myMethod() {}", "double myMethod() {}"],
                        "correct": "string myMethod() {}"
                    },
                    {
                        "question": "What is the purpose of the 'public' keyword in C?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To allow access to derived classes and friend functions", "To prevent the inheritance of a class", "To specify that a variable or method belongs to the class, not to instances of the class"],
                        "correct": "To specify that a variable or method belongs to instances of the class, not to the class itself"
                    },
                    {
                        "question": "What is the correct way to declare a method that cannot be overridden by subclasses in C?",
                        "options": ["final void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                        "correct": "final void myMethod() {}"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", strlen(\"Hello\"));' in C?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the purpose of the 'void' keyword in C?",
                        "options": ["To specify that a method does not return any value", "To allow access to derived classes and friend functions", "To prevent the inheritance of a class", "To specify that a variable or method belongs to instances of the class, not to the class itself"],
                        "correct": "To specify that a method does not return any value"
                    },
                    {
                        "question": "What is the correct way to declare a method that can be accessed by subclasses in C?",
                        "options": ["public void myMethod() {}", "private void myMethod() {}", "protected void myMethod() {}", "static void myMethod() {}"],
                        "correct": "protected void myMethod() {}"
                    },
                    {
                        "question": "What is the output of 'printf(\"%d\\n\", strlen(\"Hello\"));' in C?",
                        "options": ["5", "6", "Error", "None"],
                        "correct": "5"
                    },
                    {
                        "question": "What is the purpose of the 'virtual' keyword in C?",
                        "options": ["To specify that a variable or method belongs to instances of the class, not to the class itself", "To prevent the inheritance of a class", "To declare a constant variable", "To allow dynamic polymorphism in class hierarchies"],
                        "correct": "To allow dynamic polymorphism in class hierarchies"
                    },
                    {
                        "question": "What is the correct way to declare a constructor in C?",
                        "options": ["MyStruct()", "void MyStruct()", "constructor MyStruct()", "MyStruct constructor()"],
                        "correct": "MyStruct()"
                    }
                    ]
                },



                {
                    "id":"8",
                    "title":"Web Developement",
                    "subtitle":"HTML, CSS, JavaScript,",
                    "time":"10",
                    "questionList":[
                    {
                        "question": "What does HTML stand for?",
                        "options": ["Hyper Text Markup Language", "Hyperlinks and Text Markup Language", "Home Tool Markup Language", "Hyper Tool Markup Language"],
                        "correct": "Hyper Text Markup Language"
                    },
                    {
                        "question": "Which HTML tag is used to define an unordered list?",
                        "options": ["<ul>", "<ol>", "<list>", "<li>"],
                        "correct": "<ul>"
                    },
                    {
                        "question": "Which of the following is NOT a semantic HTML tag?",
                        "options": ["<div>", "<section>", "<nav>", "<header>"],
                        "correct": "<div>"
                    },
                    {
                        "question": "What is the correct way to link an external CSS file to an HTML document?",
                        "options": ["<link rel=\"stylesheet\" href=\"styles.css\">", "<style src=\"styles.css\">", "<stylesheet>styles.css</stylesheet>", "<css>styles.css</css>"],
                        "correct": "<link rel=\"stylesheet\" href=\"styles.css\">"
                    },
                    {
                        "question": "What does CSS stand for?",
                        "options": ["Cascading Style Sheets", "Colorful Style Sheets", "Computer Style Sheets", "Creative Style Sheets"],
                        "correct": "Cascading Style Sheets"
                    },
                    {
                        "question": "Which CSS property is used to change the text color of an element?",
                        "options": ["color", "text-color", "font-color", "text-style"],
                        "correct": "color"
                    },
                    {
                        "question": "What is the correct way to select an element with the class 'example' in CSS?",
                        "options": [".example", "#example", "element.example", "example"],
                        "correct": ".example"
                    },
                    {
                        "question": "Which of the following is NOT a valid CSS selector?",
                        "options": ["#id", ".class", "name", "element"],
                        "correct": "name"
                    },
                    {
                        "question": "What is the correct way to add padding to all sides of an element in CSS?",
                        "options": ["padding: 10px;", "padding: 10px 20px;", "padding: 10px 20px 30px;", "padding: 10px 20px 30px 40px;"],
                        "correct": "padding: 10px;"
                    },
                    {
                        "question": "Which CSS property is used to control the layout of elements?",
                        "options": ["display", "position", "float", "align"],
                        "correct": "display"
                    },
                    {
                        "question": "What does the 'float' property do in CSS?",
                        "options": ["Moves an element to the left or right of its container", "Makes text bold", "Adds a border around an element", "Creates a shadow behind an element"],
                        "correct": "Moves an element to the left or right of its container"
                    },
                    {
                        "question": "What is the correct way to create a new branch in Git?",
                        "options": ["git branch new-branch", "git checkout new-branch", "git checkout -b new-branch", "git add new-branch"],
                        "correct": "git checkout -b new-branch"
                    },
                    {
                        "question": "Which of the following is NOT a valid HTTP status code?",
                        "options": ["200", "404", "500", "302"],
                        "correct": "302"
                    },
                    {
                        "question": "What does the 'href' attribute do in an anchor tag?",
                        "options": ["Specifies the URL of the link", "Specifies the color of the link", "Specifies the size of the link", "Specifies the font of the link"],
                        "correct": "Specifies the URL of the link"
                    },
                    {
                        "question": "What does the 'src' attribute do in an image tag?",
                        "options": ["Specifies the source of the image", "Specifies the size of the image", "Specifies the color of the image", "Specifies the shape of the image"],
                        "correct": "Specifies the source of the image"
                    },
                    {
                        "question": "Which of the following is NOT a valid HTTP method?",
                        "options": ["GET", "POST", "PUSH", "DELETE"],
                        "correct": "PUSH"
                    },
                    {
                        "question": "What is the purpose of the 'method' attribute in a form tag?",
                        "options": ["Specifies the HTTP method to use when submitting the form", "Specifies the color of the form", "Specifies the size of the form", "Specifies the font of the form"],
                        "correct": "Specifies the HTTP method to use when submitting the form"
                    },
                    {
                        "question": "Which of the following is NOT a valid input type in a form?",
                        "options": ["text", "password", "number", "date-time"],
                        "correct": "date-time"
                    },
                    {
                        "question": "What is the correct way to include JavaScript in an HTML document?",
                        "options": ["<script src=\"script.js\"></script>", "<javascript>script.js</javascript>", "<js>script.js</js>", "<link href=\"script.js\">"],
                        "correct": "<script src=\"script.js\"></script>"
                    },
                    {
                        "question": "Which JavaScript function is used to change the content of an HTML element?",
                        "options": ["innerHTML", "textContent", "innerText", "append"],
                        "correct": "innerHTML"
                    },
                    {
                        "question": "What is the correct syntax to declare a JavaScript variable?",
                        "options": ["var x;", "variable x;", "x var;", "x = var;"],
                        "correct": "var x;"
                    },
                    {
                        "question": "What is the purpose of the 'addEventListener' method in JavaScript?",
                        "options": ["To attach an event handler to an element", "To create a new element", "To remove an element from the DOM", "To change the style of an element"],
                        "correct": "To attach an event handler to an element"
                    },
                    {
                        "question": "Which of the following is NOT a valid data type in JavaScript?",
                        "options": ["int", "string", "boolean", "object"],
                        "correct": "int"
                    },
                    {
                        "question": "What is the correct way to write an if statement in JavaScript?",
                        "options": ["if (x == 5) {}", "if x == 5 {}", "if x = 5 {}", "if {x == 5}"],
                        "correct": "if (x == 5) {}"
                    },
                    {
                        "question": "What does the 'typeof' operator do in JavaScript?",
                        "options": ["Returns the data type of a variable", "Concatenates two strings", "Creates a new variable", "Checks if two values are equal"],
                        "correct": "Returns the data type of a variable"
                    },
                    {
                        "question": "What is the correct way to write a comment in JavaScript?",
                        "options": ["// This is a comment", "<!-- This is a comment -->", "/* This is a comment */", "' This is a comment"],
                        "correct": "// This is a comment"
                    },
                    {
                        "question": "What is the purpose of the 'innerHTML' property in JavaScript?",
                        "options": ["To get or set the HTML content of an element", "To change the style of an element", "To create a new element", "To remove an element from the DOM"],
                        "correct": "To get or set the HTML content of an element"
                    },
                    {
                        "question": "Which of the following is NOT a valid way to declare a function in JavaScript?",
                        "options": ["function myFunction() {}", "myFunction() {}", "def myFunction() {}", "sub myFunction() {}"],
                        "correct": "sub myFunction() {}"
                    },
                    {
                        "question": "What is the purpose of the 'classList' property in JavaScript?",
                        "options": ["To get or set the classes of an element", "To create a new element", "To remove an element from the DOM", "To change the style of an element"],
                        "correct": "To get or set the classes of an element"
                    },
                    {
                        "question": "What is the correct way to check if a variable is undefined in JavaScript?",
                        "options": ["if (typeof x === 'undefined') {}", "if (x == undefined) {}", "if (x === undefined) {}", "if (x == 'undefined') {}"],
                        "correct": "if (typeof x === 'undefined') {}"
                    },
                    {
                        "question": "Which of the following is NOT a valid JavaScript event?",
                        "options": ["click", "hover", "submit", "keypress"],
                        "correct": "hover"
                    },
                    {
                        "question": "What is the purpose of the 'querySelector' method in JavaScript?",
                        "options": ["To select the first element that matches a CSS selector", "To select all elements that match a CSS selector", "To select an element by its ID", "To select an element by its class"],
                        "correct": "To select the first element that matches a CSS selector"
                    },
                    {
                        "question": "Which JavaScript method is used to remove the last element from an array?",
                        "options": ["pop()", "push()", "shift()", "unshift()"],
                        "correct": "pop()"
                    },
                    {
                        "question": "What does the 'this' keyword refer to in JavaScript?",
                        "options": ["The current object", "The global object", "The parent object", "The child object"],
                        "correct": "The current object"
                    },
                    {
                        "question": "What is the purpose of the 'getAttribute' method in JavaScript?",
                        "options": ["To get the value of an attribute of an element", "To set the value of an attribute of an element", "To remove an attribute from an element", "To check if an element has a specific attribute"],
                        "correct": "To get the value of an attribute of an element"
                    },
                    {
                        "question": "Which of the following is NOT a valid method to create a new array in JavaScript?",
                        "options": ["new Array()", "[]", "Array()", "array()"],
                        "correct": "array()"
                    },
                    {
                        "question": "What is the purpose of the 'map' method in JavaScript?",
                        "options": ["To create a new array with the results of calling a provided function on every element in the array", "To remove elements from an array", "To add elements to an array", "To sort the elements of an array"],
                        "correct": "To create a new array with the results of calling a provided function on every element in the array"
                    }
                    ]
                },



                {
                    "id":"9",
                    "title":"Reasoning",
                    "subtitle":"Advanced logical reasoning ",
                    "time":"10",
                    "questionList":[
                    {
                        "question": "If all cats are animals, and some animals are dogs, then what can we conclude?",
                        "options": ["All cats are dogs", "All dogs are cats", "Some cats are dogs", "Some dogs are cats"],
                        "correct": "Some cats are dogs"
                    },
                    {
                        "question": "If some roses are red and all red things are beautiful, then what can we conclude?",
                        "options": ["Some roses are beautiful", "All roses are beautiful", "All beautiful things are roses", "All red things are roses"],
                        "correct": "Some roses are beautiful"
                    },
                    {
                        "question": "If all birds can fly and penguins are birds, then what can we conclude?",
                        "options": ["All birds can't fly", "All penguins can fly", "All flying animals are birds", "Some birds can't fly"],
                        "correct": "Some birds can't fly"
                    },
                    {
                        "question": "If all students in a class passed the exam, and John is a student in that class, what can we conclude?",
                        "options": ["John passed the exam", "John failed the exam", "John didn't take the exam", "The exam was easy"],
                        "correct": "John passed the exam"
                    },
                    {
                        "question": "If some trees are green and all green things are plants, then what can we conclude?",
                        "options": ["Some plants are green", "Some green things are trees", "All trees are plants", "All plants are green"],
                        "correct": "Some plants are green"
                    },
                    {
                        "question": "If no cats can swim and all fish can swim, then what can we conclude?",
                        "options": ["All cats are fish", "Some cats are fish", "No cats are fish", "All fish are cats"],
                        "correct": "No cats are fish"
                    },
                    {
                        "question": "If some books are novels and some novels are mysteries, then what can we conclude?",
                        "options": ["All books are mysteries", "All mysteries are books", "All novels are books", "Some books are mysteries"],
                        "correct": "Some books are mysteries"
                    },
                    {
                        "question": "If all swans are birds and some birds are white, then what can we conclude?",
                        "options": ["All birds are white", "Some swans are white", "All white things are swans", "All birds are swans"],
                        "correct": "Some swans are white"
                    },
                    {
                        "question": "If all teachers are intelligent and some intelligent people are doctors, then what can we conclude?",
                        "options": ["Some teachers are doctors", "All teachers are doctors", "Some doctors are teachers", "All doctors are teachers"],
                        "correct": "Some teachers are doctors"
                    },
                    {
                        "question": "If all squares are rectangles and some rectangles are red, then what can we conclude?",
                        "options": ["All squares are red", "Some squares are red", "All red things are squares", "All rectangles are squares"],
                        "correct": "Some squares are red"
                    },
                    {
                        "question": "If all A are B, and all B are C, then what can we conclude?",
                        "options": ["All A are C", "Some A are C", "No A are C", "All C are A"],
                        "correct": "All A are C"
                    },
                    {
                        "question": "If some animals are dogs and all dogs are loyal, then what can we conclude?",
                        "options": ["All animals are loyal", "Some animals are loyal", "No animals are loyal", "All loyal things are animals"],
                        "correct": "Some animals are loyal"
                    },
                    {
                        "question": "If all apples are fruits and all fruits are healthy, then what can we conclude?",
                        "options": ["All apples are healthy", "Some apples are healthy", "No apples are healthy", "All healthy things are apples"],
                        "correct": "All apples are healthy"
                    },
                    {
                        "question": "If some politicians are corrupt and all corrupt people are dishonest, then what can we conclude?",
                        "options": ["All politicians are dishonest", "Some politicians are dishonest", "No politicians are dishonest", "All dishonest people are politicians"],
                        "correct": "Some politicians are dishonest"
                    },
                    {
                        "question": "If all laptops are electronic devices and some electronic devices are expensive, then what can we conclude?",
                        "options": ["All laptops are expensive", "Some laptops are expensive", "No laptops are expensive", "All expensive things are laptops"],
                        "correct": "Some laptops are expensive"
                    },
                    {
                        "question": "If all dogs are animals and all animals are creatures, then what can we conclude?",
                        "options": ["All dogs are creatures", "Some dogs are creatures", "No dogs are creatures", "All creatures are dogs"],
                        "correct": "All dogs are creatures"
                    },
                    {
                        "question": "If some birds are penguins and all penguins can swim, then what can we conclude?",
                        "options": ["All birds can swim", "Some birds can swim", "No birds can swim", "All swimming animals are birds"],
                        "correct": "Some birds can swim"
                    },
                    {
                        "question": "If all triangles have three sides and some three-sided shapes are polygons, then what can we conclude?",
                        "options": ["All triangles are polygons", "Some triangles are polygons", "No triangles are polygons", "All polygons are triangles"],
                        "correct": "Some triangles are polygons"
                    },
                    {
                        "question": "If some oranges are fruits and all fruits are healthy, then what can we conclude?",
                        "options": ["Some oranges are healthy", "All oranges are healthy", "No oranges are healthy", "All healthy things are oranges"],
                        "correct": "Some oranges are healthy"
                    },
                    {
                        "question": "If all politicians are corrupt and some corrupt people are rich, then what can we conclude?",
                        "options": ["All politicians are rich", "Some politicians are rich", "No politicians are rich", "All rich people are politicians"],
                        "correct": "Some politicians are rich"
                    },
                    {
                        "question": "If all books are knowledge and all knowledge is power, then what can we conclude?",
                        "options": ["All books are power", "Some books are power", "No books are power", "All power is books"],
                        "correct": "Some books are power"
                    },
                    {
                        "question": "If all students are hardworking and some hardworking people are successful, then what can we conclude?",
                        "options": ["All students are successful", "Some students are successful", "No students are successful", "All successful people are students"],
                        "correct": "Some students are successful"
                    },
                    {
                        "question": "If some cars are electric and all electric vehicles are eco-friendly, then what can we conclude?",
                        "options": ["Some cars are eco-friendly", "All cars are eco-friendly", "No cars are eco-friendly", "All eco-friendly vehicles are cars"],
                        "correct": "Some cars are eco-friendly"
                    },
                    {
                        "question": "If all squares are rectangles and all rectangles are polygons, then what can we conclude?",
                        "options": ["All squares are polygons", "Some squares are polygons", "No squares are polygons", "All polygons are squares"],
                        "correct": "All squares are polygons"
                    },
                    {
                        "question": "If all lawyers are intelligent and some intelligent people are wealthy, then what can we conclude?",
                        "options": ["All lawyers are wealthy", "Some lawyers are wealthy", "No lawyers are wealthy", "All wealthy people are lawyers"],
                        "correct": "Some lawyers are wealthy"
                    },
                    {
                        "question": "If some clouds are white and all white things are fluffy, then what can we conclude?",
                        "options": ["Some clouds are fluffy", "All clouds are fluffy", "No clouds are fluffy", "All fluffy things are clouds"],
                        "correct": "Some clouds are fluffy"
                    },
                    {
                        "question": "If all apples are fruits and all fruits are delicious, then what can we conclude?",
                        "options": ["All apples are delicious", "Some apples are delicious", "No apples are delicious", "All delicious things are apples"],
                        "correct": "All apples are delicious"
                    },
                    {
                        "question": "If some politicians are honest and all honest people are respected, then what can we conclude?",
                        "options": ["Some politicians are respected", "All politicians are respected", "No politicians are respected", "All respected people are politicians"],
                        "correct": "Some politicians are respected"
                    },
                    {
                        "question": "If all birds can fly and some flying animals are birds, then what can we conclude?",
                        "options": ["All flying animals can fly", "Some flying animals can fly", "No flying animals can fly", "All birds can't fly"],
                        "correct": "Some flying animals can fly"
                    },
                    {
                        "question": "If some cats are black and all black things are mysterious, then what can we conclude?",
                        "options": ["Some cats are mysterious", "All cats are mysterious", "No cats are mysterious", "All mysterious things are cats"],
                        "correct": "Some cats are mysterious"
                    },
                    {
                        "question": "If all pens are stationery items and some stationery items are cheap, then what can we conclude?",
                        "options": ["Some pens are cheap", "All pens are cheap", "No pens are cheap", "All cheap things are pens"],
                        "correct": "Some pens are cheap"
                    },
                    {
                        "question": "If all cats are felines and all felines are carnivores, then what can we conclude?",
                        "options": ["All cats are carnivores", "Some cats are carnivores", "No cats are carnivores", "All carnivores are cats"],
                        "correct": "All cats are carnivores"
                    },
                    {
                        "question": "If all managers are leaders and some leaders are efficient, then what can we conclude?",
                        "options": ["Some managers are efficient", "All managers are efficient", "No managers are efficient", "All efficient people are managers"],
                        "correct": "Some managers are efficient"
                    },
                    {
                        "question": "If all monkeys are mammals and some mammals are friendly, then what can we conclude?",
                        "options": ["Some monkeys are friendly", "All monkeys are friendly", "No monkeys are friendly", "All friendly animals are monkeys"],
                        "correct": "Some monkeys are friendly"
                    },
                    {
                        "question": "If some countries are beautiful and all beautiful places are tourist destinations, then what can we conclude?",
                        "options": ["Some countries are tourist destinations", "All countries are tourist destinations", "No countries are tourist destinations", "All tourist destinations are countries"],
                        "correct": "Some countries are tourist destinations"
                    },
                    {
                        "question": "If all roses are flowers and some flowers are fragrant, then what can we conclude?",
                        "options": ["Some roses are fragrant", "All roses are fragrant", "No roses are fragrant", "All fragrant things are roses"],
                        "correct": "Some roses are fragrant"
                    },
                    {
                        "question": "If all lawyers are articulate and some articulate people are persuasive, then what can we conclude?",
                        "options": ["Some lawyers are persuasive", "All lawyers are persuasive", "No lawyers are persuasive", "All persuasive people are lawyers"],
                        "correct": "Some lawyers are persuasive"
                    },
                    {
                        "question": "If some countries are islands and all islands are surrounded by water, then what can we conclude?",
                        "options": ["Some countries are surrounded by water", "All countries are surrounded by water", "No countries are surrounded by water", "All water bodies are countries"],
                        "correct": "Some countries are surrounded by water"
                    }
                    ]
                },



                {
                    "id":"10",
                    "title":"Interview Skills",
                    "subtitle":"Interview, Communication skills",
                    "time":"10",
                    "questionList":[
                    {
                        "question": "What is the purpose of a resume in a job application process?",
                        "options": ["To showcase your skills and experiences", "To list your hobbies and interests", "To demonstrate your social media presence", "To provide references"],
                        "correct": "To showcase your skills and experiences"
                    },
                    {
                        "question": "What is the STAR method used for in interviews?",
                        "options": ["To answer behavioral interview questions", "To demonstrate technical skills", "To negotiate salary", "To describe career goals"],
                        "correct": "To answer behavioral interview questions"
                    },
                    {
                        "question": "What does 'SWOT' stand for in the context of interview preparation?",
                        "options": ["Strengths, Weaknesses, Opportunities, Threats", "Skills, Work experience, Objectives, Traits", "Success, Winning, Optimization, Tenacity", "Safety, Wellness, Organization, Time management"],
                        "correct": "Strengths, Weaknesses, Opportunities, Threats"
                    },
                    {
                        "question": "What should you do to prepare for a job interview?",
                        "options": ["Research the company and practice common interview questions", "Arrive late to make a lasting impression", "Bring your friends or family members along", "Use informal language and slang during the interview"],
                        "correct": "Research the company and practice common interview questions"
                    },
                    {
                        "question": "What is the purpose of sending a thank-you note after an interview?",
                        "options": ["To express gratitude and reinforce interest in the position", "To criticize the interviewer's questions", "To demand immediate feedback", "To ask for a higher salary"],
                        "correct": "To express gratitude and reinforce interest in the position"
                    },
                    {
                        "question": "What should you do if you don't know the answer to an interview question?",
                        "options": ["Stay calm and admit that you don't know, then explain how you would find the answer", "Make up an answer to impress the interviewer", "Get defensive and argue with the interviewer", "Refuse to answer the question"],
                        "correct": "Stay calm and admit that you don't know, then explain how you would find the answer"
                    },
                    {
                        "question": "What is the purpose of a cover letter in a job application?",
                        "options": ["To introduce yourself and explain why you're a good fit for the position", "To provide a summary of your resume", "To list your academic qualifications", "To request a job offer"],
                        "correct": "To introduce yourself and explain why you're a good fit for the position"
                    },
                    {
                        "question": "What should you do if you're asked an illegal interview question?",
                        "options": ["Politely refuse to answer and explain why the question is inappropriate", "Answer the question truthfully regardless of its legality", "File a lawsuit against the interviewer", "Laugh it off and change the subject"],
                        "correct": "Politely refuse to answer and explain why the question is inappropriate"
                    },
                    {
                        "question": "What is the purpose of practicing mock interviews?",
                        "options": ["To improve your interview skills and boost confidence", "To waste time and energy", "To memorize scripted answers", "To learn about the interviewer's personal life"],
                        "correct": "To improve your interview skills and boost confidence"
                    },
                    {
                        "question": "What does 'fit' refer to in the context of hiring?",
                        "options": ["Cultural fit within the company", "Physical fitness of the candidate", "Ability to perform job duties", "Educational background"],
                        "correct": "Cultural fit within the company"
                    },
                    {
                        "question": "What should you do if you're asked about your weaknesses in an interview?",
                        "options": ["Provide a genuine weakness and explain how you're working to improve it", "Say you don't have any weaknesses", "Avoid the question by talking about your strengths", "Criticize your former employers"],
                        "correct": "Provide a genuine weakness and explain how you're working to improve it"
                    },
                    {
                        "question": "What is the purpose of researching the company before an interview?",
                        "options": ["To understand its products, services, and culture", "To find out personal information about the interviewer", "To memorize the CEO's name", "To request a signing bonus"],
                        "correct": "To understand its products, services, and culture"
                    },
                    {
                        "question": "What does 'behavioral interview' mean?",
                        "options": ["An interview style that focuses on past behavior and experiences", "An interview conducted in a behavioral health facility", "An interview conducted while walking or exercising", "An interview conducted by behavior specialists"],
                        "correct": "An interview style that focuses on past behavior and experiences"
                    },
                    {
                        "question": "What should you do if you're asked about your salary expectations?",
                        "options": ["Provide a range based on industry standards and your experience", "Demand the highest salary possible", "Refuse to answer the question", "Lie about your current salary"],
                        "correct": "Provide a range based on industry standards and your experience"
                    },
                    {
                        "question": "What is the purpose of networking in the job search process?",
                        "options": ["To build professional relationships and explore job opportunities", "To waste time on social media", "To gossip about former employers", "To criticize the competition"],
                        "correct": "To build professional relationships and explore job opportunities"
                    },
                    {
                        "question": "What should you do to make a good first impression in an interview?",
                        "options": ["Dress professionally and arrive on time", "Show up late and make excuses", "Ignore the interviewer's questions", "Use slang and informal language"],
                        "correct": "Dress professionally and arrive on time"
                    },
                    {
                        "question": "What is the purpose of asking questions at the end of an interview?",
                        "options": ["To demonstrate your interest in the position and learn more about the company", "To show off your knowledge and skills", "To criticize the interviewer's questions", "To demand immediate feedback"],
                        "correct": "To demonstrate your interest in the position and learn more about the company"
                    },
                    {
                        "question": "What should you do if you're asked about conflicts with coworkers in a previous job?",
                        "options": ["Provide a diplomatic answer and focus on how you resolved the conflict", "Blame your coworkers for the conflict", "Avoid the question by changing the subject", "Lie about your experience"],
                        "correct": "Provide a diplomatic answer and focus on how you resolved the conflict"
                    },
                    {
                        "question": "What is the purpose of sending a follow-up email after an interview?",
                        "options": ["To thank the interviewer and express interest in the position", "To criticize the interviewer's questions", "To demand immediate feedback", "To ask for a higher salary"],
                        "correct": "To thank the interviewer and express interest in the position"
                    },
                    {
                        "question": "What should you do if you're asked about your long-term career goals?",
                        "options": ["Provide a realistic and ambitious answer that aligns with the job you're applying for", "Say you don't have any career goals", "Lie about your career goals to impress the interviewer", "Change the subject and talk about your personal life"],
                        "correct": "Provide a realistic and ambitious answer that aligns with the job you're applying for"
                    },
                    {
                        "question": "What is the purpose of a job interview?",
                        "options": ["To assess a candidate's qualifications and fit for a position", "To waste time and energy", "To make the candidate feel uncomfortable", "To criticize the competition"],
                        "correct": "To assess a candidate's qualifications and fit for a position"
                    },
                    {
                        "question": "What should you do if you're asked about your greatest professional achievement?",
                        "options": ["Provide a specific example and explain why it was meaningful", "Say you don't have any achievements", "Lie about your achievements to impress the interviewer", "Change the subject and talk about your personal life"],
                        "correct": "Provide a specific example and explain why it was meaningful"
                    },
                    {
                        "question": "What is the purpose of practicing active listening in an interview?",
                        "options": ["To demonstrate your interest and understanding of the conversation", "To interrupt the interviewer and talk about yourself", "To make the interviewer feel uncomfortable", "To criticize the competition"],
                        "correct": "To demonstrate your interest and understanding of the conversation"
                    },
                    {
                        "question": "What should you do if you're asked about your leadership experience?",
                        "options": ["Provide examples of times you demonstrated leadership skills", "Say you've never been a leader", "Lie about your leadership experience", "Change the subject and talk about your personal life"],
                        "correct": "Provide examples of times you demonstrated leadership skills"
                    },
                    {
                        "question": "What is the purpose of practicing your elevator pitch?",
                        "options": ["To introduce yourself and summarize your qualifications in a concise manner", "To waste time and energy", "To memorize scripted answers", "To criticize the competition"],
                        "correct": "To introduce yourself and summarize your qualifications in a concise manner"
                    },
                    {
                        "question": "What should you do if you're asked about your greatest weakness in an interview?",
                        "options": ["Provide a genuine weakness and explain how you're working to improve it", "Say you don't have any weaknesses", "Lie about your weaknesses", "Change the subject and talk about your strengths"],
                        "correct": "Provide a genuine weakness and explain how you're working to improve it"
                    },
                    {
                        "question": "What is the purpose of practicing interview questions with a friend or family member?",
                        "options": ["To simulate a real interview and receive feedback", "To waste time and energy", "To memorize scripted answers", "To criticize the competition"],
                        "correct": "To simulate a real interview and receive feedback"
                    },
                    {
                        "question": "What should you do if you're asked about a gap in your employment history?",
                        "options": ["Provide an honest explanation and focus on what you learned during that time", "Lie about the reason for the gap", "Avoid the question by changing the subject", "Blame your former employer for the gap"],
                        "correct": "Provide an honest explanation and focus on what you learned during that time"
                    },
                    {
                        "question": "What is the purpose of researching common interview questions?",
                        "options": ["To prepare for potential questions and formulate thoughtful responses", "To waste time and energy", "To memorize scripted answers", "To criticize the competition"],
                        "correct": "To prepare for potential questions and formulate thoughtful responses"
                    },
                    {
                        "question": "What should you do if you're asked about your salary history?",
                        "options": ["Politely decline to answer and redirect the conversation to your salary expectations", "Provide a truthful answer", "Lie about your salary history", "Refuse to answer any questions about salary"],
                        "correct": "Politely decline to answer and redirect the conversation to your salary expectations"
                    },
                    {
                        "question": "What is the purpose of researching the interviewer before an interview?",
                        "options": ["To learn about their background and interests", "To waste time and energy", "To memorize scripted answers", "To criticize the competition"],
                        "correct": "To learn about their background and interests"
                    },
                    {
                        "question": "What should you do if you're asked about your reasons for leaving a previous job?",
                        "options": ["Provide a diplomatic answer and focus on what you learned from the experience", "Blame your former employer for your departure", "Avoid the question by changing the subject", "Lie about the reason for leaving"],
                        "correct": "Provide a diplomatic answer and focus on what you learned from the experience"
                    },
                    {
                        "question": "What is the purpose of conducting informational interviews?",
                        "options": ["To learn more about a specific industry or company from professionals", "To waste time and energy", "To memorize scripted answers", "To criticize the competition"],
                        "correct": "To learn more about a specific industry or company from professionals"
                    },
                    {
                        "question": "What should you do if you're asked about your most challenging work experience?",
                        "options": ["Provide a specific example and explain how you overcame the challenge", "Say you've never faced any challenges at work", "Lie about your work experience", "Change the subject and talk about your personal life"],
                        "correct": "Provide a specific example and explain how you overcame the challenge"
                    },
                    {
                        "question": "What is the purpose of practicing your body language for an interview?",
                        "options": ["To appear confident and engaged during the interview", "To slouch and avoid eye contact", "To make the interviewer feel uncomfortable", "To criticize the competition"],
                        "correct": "To appear confident and engaged during the interview"
                    }
                    ]
                }



            ]
        """

        try {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val title = jsonObject.getString("title")
                val subtitle = jsonObject.getString("subtitle")
                val time = jsonObject.getString("time")
                val questionListJsonArray = jsonObject.getJSONArray("questionList")
                val questionList = mutableListOf<QuestionModel>()

                // Convert JSONArray to MutableList for shuffling
                val tempQuestionList = mutableListOf<JSONObject>()
                for (j in 0 until questionListJsonArray.length()) {
                    tempQuestionList.add(questionListJsonArray.getJSONObject(j))
                }
                // Shuffle the list of questions
                shuffle(tempQuestionList)

                // Select first 10 questions
                val selectedQuestions = tempQuestionList.subList(0, minOf(tempQuestionList.size, 10))
                selectedQuestions.forEach { usedQuestionIndices.add(tempQuestionList.indexOf(it)) }


                for (questionObject in selectedQuestions) {
                    val question = questionObject.getString("question")
                    val optionsArray = questionObject.getJSONArray("options")
                    val options = mutableListOf<String>()
                    for (k in 0 until optionsArray.length()) {
                        options.add(optionsArray.getString(k))
                    }
                    val correct = questionObject.getString("correct")
                    val questionModel = QuestionModel(question, options, correct)
                    questionList.add(questionModel)
                }
                val quizModel = QuizModel(id, title, subtitle, time, questionList)
                quizModelList.add(quizModel)
            }

            setupRecyclerView()
        } catch (e: JSONException) {
            e.printStackTrace()
            // Handle JSON parsing error
        }
    }
}




