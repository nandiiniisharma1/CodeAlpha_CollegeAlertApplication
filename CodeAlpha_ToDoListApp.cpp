#include <iostream>
#include <fstream>
#include <vector>
#include <string>
using namespace std;

class Task {
public:
    string name;
    string category;
    bool completed;

    Task(string n, string c) : name(n), category(c), completed(false) {}
};

vector<Task> tasks;
const string FILE_NAME = "tasks.txt";

void saveTasks() {
    ofstream file(FILE_NAME);
    for (auto& t : tasks)
        file << t.name << "|" << t.category << "|" << t.completed << "\n";
    file.close();
}

void loadTasks() {
    ifstream file(FILE_NAME);
    if (!file.is_open()) return;
    string line;
    while (getline(file, line)) {
        size_t p1 = line.find('|'), p2 = line.rfind('|');
        if (p1 == string::npos || p2 == string::npos || p1 == p2) continue;
        Task t(line.substr(0, p1), line.substr(p1 + 1, p2 - p1 - 1));
        t.completed = (line.substr(p2 + 1) == "1");
        tasks.push_back(t);
    }
    file.close();
}

void addTask() {
    string name, category;
    cin.ignore();
    cout << "Enter task name: ";
    getline(cin, name);
    cout << "Enter category (Work/Personal/Other): ";
    getline(cin, category);
    tasks.push_back(Task(name, category));
    saveTasks();
    cout << "Task added successfully!\n";
}

void viewTasks(bool showCompleted) {
    cout << "\n--- " << (showCompleted ? "Completed" : "Pending") << " Tasks ---\n";
    int count = 0;
    for (int i = 0; i < (int)tasks.size(); i++) {
        if (tasks[i].completed == showCompleted) {
            cout << "[" << i + 1 << "] " << tasks[i].name
                 << " [" << tasks[i].category << "]\n";
            count++;
        }
    }
    if (count == 0) cout << "No tasks found.\n";
}

void markCompleted() {
    viewTasks(false);
    cout << "Enter task number to mark as completed: ";
    int num; cin >> num;
    num--;
    if (num >= 0 && num < (int)tasks.size() && !tasks[num].completed) {
        tasks[num].completed = true;
        saveTasks();
        cout << "Task marked as completed!\n";
    } else {
        cout << "Invalid choice.\n";
    }
}

void deleteTask() {
    viewTasks(false);
    cout << "Enter task number to delete: ";
    int num; cin >> num;
    num--;
    if (num >= 0 && num < (int)tasks.size()) {
        tasks.erase(tasks.begin() + num);
        saveTasks();
        cout << "Task deleted successfully!\n";
    } else {
        cout << "Invalid choice.\n";
    }
}

int main() {
    loadTasks();
    int choice;
    do {
        cout << "\n===== TO-DO LIST APPLICATION =====\n";
        cout << "1. Add Task\n";
        cout << "2. View Pending Tasks\n";
        cout << "3. View Completed Tasks\n";
        cout << "4. Mark Task as Completed\n";
        cout << "5. Delete Task\n";
        cout << "0. Exit\n";
        cout << "Choice: ";
        cin >> choice;

        switch (choice) {
            case 1: addTask(); break;
            case 2: viewTasks(false); break;
            case 3: viewTasks(true); break;
            case 4: markCompleted(); break;
            case 5: deleteTask(); break;
            case 0: cout << "Goodbye!\n"; break;
            default: cout << "Invalid option. Try again.\n";
        }
    } while (choice != 0);
    return 0;
}
