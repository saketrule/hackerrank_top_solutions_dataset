#include <stdio.h>

int main()
{
	int stickLengthArray[1001];
	int numOfSticks , i =0 ;
	int min ;
	long long int numOfCuts = 0;
	int flag = 0 , new_min;

	scanf("%d",&numOfSticks);

	scanf("%d", &stickLengthArray[0] );

	min = stickLengthArray[0];

	if( stickLengthArray[i] != 0 )
		numOfCuts++;

	for( i = 1 ; i < numOfSticks ; i++ )
	{
		scanf("%d",&stickLengthArray[i]);

		if( stickLengthArray[i] != 0 )
			numOfCuts++;

		if( min > stickLengthArray[i] )
			min = stickLengthArray[i];
	}

	//if(numOfCuts != 0)
	//	printf("%lld\n",numOfCuts );

//	printf("min =%d\n", min);

	//for( i = 0 ; i < numOfSticks ; i++ )
	//	stickLengthArray[i] -= min;
	

	while(1)
	{
		flag = 0 ;

		new_min = 99999;

		numOfCuts = 0;

		for( i = 0 ; i < numOfSticks ; i++ )
		{
			if( stickLengthArray[i] != 0 )
			{
				stickLengthArray[i] -= min;
				numOfCuts++;
			}

			if( new_min > stickLengthArray[i] && stickLengthArray[i] != 0 )
				new_min = stickLengthArray[i];

		}

		min = new_min;

		if(numOfCuts != 0)
			printf("%lld\n", numOfCuts );

		for( i = 0 ; i < numOfSticks ; i++ )
		{

			flag = 0;

			if( stickLengthArray[i] > new_min )
			{
				flag = 1;
				break;
			}
		}

		if( flag == 0  )
			break;


	}



	numOfCuts = 0;

	flag = 0;

	for( i = 0 ; i < numOfSticks ; i++ )
	{
		if( stickLengthArray[i] == new_min )
		{
			numOfCuts++;
			flag = 1;
		}
	}


	if( flag == 1 )
		printf("%lld\n", numOfCuts );





	return 0;

}