#include<stdio.h>
#include<conio.h>
#include<string.h>

void main()
{
  int ch,ch1,ch3,i,l;
	char fname[20],fname2[20],c;
	FILE *fp;
	FILE *fp1;
	FILE *fp2;
	do
	{
	clrscr();
	printf("Menu:\n1.File\n2.Edit\n3.EXIT\n");
	scanf("%d",&ch);
	switch(ch)
	{
		case 1:
			printf("\nFILE:\n1.CREATE\n2.OPEN\n3.APPEND\n4.Exit\n Enter your choice:");
			scanf("%d",&ch1);
			switch(ch1)
			{
				case 1:
					printf("Enter the file name\n");
					scanf("%s",fname);
					fp=fopen(fname,"w");
					printf("enter contents,$ to end:\n");
					while((c=getchar())!='$')
					{
						putc(c,fp);
					}
					fclose(fp);
					break;
					getch();
				case 2:
					printf("Enter the name of the file to be opened\n");
					scanf("%s",fname);
					fp=fopen(fname,"r");
					printf("contents are:\n");
					while((c=getc(fp))!=EOF)
					{
						printf("%c",c);
					}
					fclose(fp);
					getch();
				break;
				case 3:
					printf("Enter the file name: ");
					scanf("%s",fname);
					printf("Enter new filename: ");
					scanf("%s",fname2);
					fp=fopen(fname,"r");
					fp2=fopen(fname2,"w");
					while((c=getc(fp))!=EOF)
					{
						putc(c,fp2);
					}
					fclose(fp);
					printf("\nenter data to be appended,'$' to end: ");
					while((c=getchar())!='$')
					{
						putc(c,fp2);
					}
					fclose(fp2);
					getch();
				break;
				case 4: exit(0);

				break;
			}
		break;
		case 2:
		       printf("Edit:\n1.Insert\n2.Modify\n3.Delete\nEnter your choice:");
		       scanf("%d",&ch3);

		       printf("Enter the file name.\n");
		       scanf("%s",&fname);
		       fp=fopen(fname,"r");
		       printf("enter new name: ");
		       scanf("%s",&fname2);
		       fp1=fopen(fname2,"w");
		       switch(ch3)
		       {
		       case 1:
					printf("Enter the line no. for insert:");
					scanf("%d",&l);

					for(i=1;i<=l;i++)
					{       printf("saving line %d",i);
						while((c=getc(fp))!='\n')
						putc(c,fp1);
						putc('\n',fp1);
					}
					printf("Enter the text to insert:");
					while((c=getch())!='$')
					{       putch(c);
						putc(c,fp1);
					}
					putc('\n',fp1);
					while((c=getc(fp))!=EOF)
					{
						putc(c,fp1);
					}
					fclose(fp);
					fclose(fp1);
				break;
				case 2:

					printf("\nEnter the line number which is to be modified:");
					scanf("%d",&l);

					for(i=1;i<=l;i++)
					{
						while((c=getc(fp))!='\n')
						{
							putc(c,fp1);
						}

						putc('\n',fp1);
					}
					printf("\nEnter the line to be inserted:");
					while((c=getch())!='$')
					{       putch(c);
						putc(c,fp1);
					}
					while((c=getc(fp))!='\n')
					{
					}
					putc('\n',fp1);
					while((c=getc(fp))!=EOF)
					{
						putc(c,fp1);
					}
					fclose(fp);
					fclose(fp1);

				break;
				case 3:

					printf("\nEnter the line number which you want to delete:");
					scanf("%d",&l);
					for(i=0;i<l;i++)
					{
						while((c=getc(fp))!='\n')
						{
							putc(c,fp1);
						}

						putc('\n',fp1);
					}
					while((c=getc(fp))!='\n')
					{
					}
					while((c=getc(fp))!=EOF)
					{
						putc(c,fp1);
					}
					fclose(fp);
					fclose(fp1);

				break;
		       }

		break;
	}
	}while(ch!=3);
	getch();
}

