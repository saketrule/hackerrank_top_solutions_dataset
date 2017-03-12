import Control.Monad
import Data.List

main = do
 n<-getLine
 numberlist<-getLine
 let numbers = map (\x->read x::Int) (words numberlist )
 mapM_  putStrLn  $ map (\x->show x) $ list_of_same_numbers [length numbers] (sort numbers) where
       list_of_same_numbers::[Int]->[Int]->[Int]
       list_of_same_numbers returnList [] = (reverse.(drop 1).reverse) returnList
       list_of_same_numbers returnList  (x:xs) = list_of_same_numbers (returnList ++[(length restOfList )])  restOfList where
                 restOfList = dropWhile (==x) xs