#include <iostream.h>
#include<conio.h>
#include <fstream.h>
#include <string.h>
#include <stdlib.h>

struct symtab_row {
  char name[32];
	int type;
	int address;
	int size;
	union{int ival;float fval;char cval;double dval;} value;
} symtab[100];

int typify(char* str)
{
	if(!strcmp(str,"int"))
		return 1;
	else if(!strcmp(str,"float"))
		return 2;
	else if(!strcmp(str,"char"))
		return 3;
	else if(!strcmp(str,"double"))
		return 4;
	else return -1;
}
int find(char *id,int n)
{
	for(int i = 0 ; i < n ;i++)
		if(!strcmp(symtab[i].name,id))
			return i;
	return -1;
}
void insert(char* name,int &n,int dtype)
{
	int address;
	if(n == -1)
		address = 1000;
	else
		address = symtab[n].address + symtab[n].size;
	n++;
	switch(dtype)
	{
		case 1:symtab[n].size = 2;break;
		case 2:symtab[n].size = 4;break;
		case 3:symtab[n].size = 1;break;
		case 4:symtab[n].size = 8;break;
	}
	symtab[n].address = address;
	strcpy(symtab[n].name,name);
	symtab[n].type = dtype;
}
void update(int i,char* value)
{
	switch(symtab[i].type)
	{
		case 1 : symtab[i].value.ival = atoi(value);break;
		case 2 : symtab[i].value.fval = atof(value);break;
		case 3 : symtab[i].value.cval = value[1];break;
		case 4 : symtab[i].value.dval = atof(value);break;
	}
}
int main()
{
	clrscr();
	char* fname;
	cout << "Enter the file name : ";
	cin >> fname;
	ifstream fin(fname);
	if(fin)
	{
	       char line[100];
	       char id[100];
	       int tab_row = -1;
	       int z = 0;
	       fin.getline(line,100);
	       int dtype=0;
	       while(!fin.eof())
	       {
			dtype = 0;
			z = 0;
			for(int i = 0 ; i< strlen(line) ; i++)
			{
				if(line[i] != ' ' && line[i] != '\t' && line[i] != '\n' && line[i] != ',' && line[i] != ';')
				{
					id[z++] = line[i];
				}
				else
				{
					id[z] = 0;
					int kind = typify(id);
					if(kind > 0) {
						dtype = kind;
						z = 0;
						continue;
					}
					if(dtype != 0 || find(id,tab_row)!=-1)
					{
						int f = find(id,tab_row);
						if(f == -1) {
							insert(id,tab_row,dtype);
							f = tab_row;
							z = 0;
							while(line[i] == '\t' || line[i] == ' ')
								i++;
							if(line[i] == '='){
								i++;
								while(line[i] == '\t' || line[i] == ' ')
									i++;
								z = 0;
								while(line[i] != ';' && line[i] != ' ')
									id[z++] = line[i++];
								id[z] = 0;
								z = 0;
								update(f,id);
								id[0] = 0;
							}

						}
						else
						{
							while(line[i] == '\t' || line[i] == ' ')
								i++;
							if(line[i] == '='){
								i++;
								while(line[i] == '\t' || line[i] == ' ')
									i++;
								z = 0;
								while(line[i] != ';' && line[i] != ' ')
									id[z++] = line[i++];
								id[z] = 0;
								z = 0;
								update(f,id);
							}
						}
					}


				}
			}
			fin.getline(line,100);
	       }
	       cout << "Name\tAddress\tType\tValue\n";
	       for(int i = 0 ; i <= tab_row ; i++)
	       {
			cout << symtab[i].name << "\t" << symtab[i].address << "\t";
			switch(symtab[i].type)
			{
				case 1:cout << "int\t" << symtab[i].value.ival;break;
				case 2:cout << "float\t" << symtab[i].value.fval;break;
				case 3:cout << "char\t" << symtab[i].value.cval;break;
				case 4:cout << "double\t" << symtab[i].value.dval;break;
			}
			cout << endl;
	       }
	}
	else {
		cout << "Bad input file" << endl;
	}
	getch();
	return 0;
}
