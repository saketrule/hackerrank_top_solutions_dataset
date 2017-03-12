import Data.List
import Data.Char
import qualified Data.ByteString.Lazy.Char8 as BS

main :: IO ()
main = BS.getContents >>= mapM_ print. validate. map readIntegerArray. BS.lines

validate :: [[Integer]] -> [Int]
validate [[n], arr]
  | n /= fromIntegral (length arr) = error " n /= length arr"
  | n < 1 || n > 1000 = error "n is out of bound"
  | any (\x -> x < 1 || x > 1000) arr = error "element is out of bound"
  | otherwise = solve arr
    where
      solve :: [Integer] -> [Int]
      solve [] = []
      solve xs =  length xs: (solve. filter (>mn)$ xs)
        where
          mn = minimum xs
validate _ = error "format doesn't match"

getInteger = (\(Just (x_yzpqr,_)) -> x_yzpqr). BS.readInteger
readIntegerArray input_pqr =
  case x_yzpqr of
    Nothing               -> []
    Just (y_zpqr, ys_pqr) -> y_zpqr : readIntegerArray ys_pqr
  where
    x_yzpqr = BS.readInteger. BS.dropWhile isSpace $ input_pqr
