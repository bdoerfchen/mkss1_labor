We have chosen the persistence-based approach to prepare for replacing the implementation with an actual database in the future.
With the collection-based approach it is inherently more difficult to synchronize data entries across systems.
Therefore, we decided that the save operation has to be triggered explicitly.
Otherwise, we would have to track every change of the entry objects.
