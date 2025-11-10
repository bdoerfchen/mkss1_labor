### Assessment of the clean architecture
We appreciate the gained flexibility from decoupling the layers.
However, we found the overhead to be quite big, in relation to the usefulness of the code.
To mitigate this we used existing interfaces like the functional Consumer interface to implement the Output boundaries,
thereby significantly reducing boilerplate in our use case.