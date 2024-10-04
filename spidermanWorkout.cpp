#include <iostream>
#include <algorithm>
#include <array>

int numTestScenarios, numDistances;     // Inputs 1 <= N <= 101 , and M <= 40, respectively
std::array <int, 101> heights;          // Array storing each scenario's distances
std::array <std::array <int, 1002>, 101> distances;     // Array storing each scenario's potential solution

int minHeight(int i, int H = 0){        // Find the minimum building height for each test scenario
    if (i == numDistances){     // If we've reached the last distance,
        if (H) return 2000;         // If the height is not 0, return 2000 (error value)
        else return H;              // If the height is zero (ground level), return the height
    }

    if(H < 0) return 2000;      // If the height is less than zero (-1) return error

    if(distances.at(i).at(H) != -1) return distances.at(i).at(H);       // If the distance for the current scenario is valid, return it

    // Set the smallest distance to be the smallest height found across all possible solutions 
    return distances.at(i).at(H) = std::max(H, std::min(minHeight(i + 1, H + heights.at(i)), minHeight(i + 1, H - heights.at(i))));
}

void output(int i, int H){
    if(i == numDistances || H < 0) return;      // If reached end of trials, return nothing

    if(minHeight(i + 1, H + heights.at(i)) < minHeight(i + 1, H - heights.at(i))){      // If going up results in a lower building height, return that value and output 'U'
        std::cout << 'U';
        return output(i + 1, H + heights.at(i));
    }
    
    std::cout << 'D';       // Otherwise, output 'D' and return the "down" value 
    return output(i + 1, H - heights.at(i));
}

int main() {
    std::cin >> numTestScenarios;       // Input N

    while(numTestScenarios--){          // Decrease N with each iteration
        std::cin >> numDistances;       // Input M <= 40

        std::fill(distances.front().begin(), distances.back().end(), -1);       // Replace every value in set of distances with -1

        for(int i = 0; i < numDistances; i++) std::cin >> heights.at(i);        // For 0 to M, input the distances for the current scenario

        if(minHeight(0) == 2000) std::cout << "IMPOSSIBLE";     // If the lowest building height is the error value, return "impossible"
        else output(0, 0);                                      // Otherwise, begin outputting the directions for the lowest building height

        std::cout << std::endl;
    }

    return 0;
}
