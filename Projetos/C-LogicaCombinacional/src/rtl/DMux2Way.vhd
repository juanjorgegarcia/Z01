library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity DMux2Way is
	port ( 
			a:   in  STD_LOGIC;
			sel: in  STD_LOGIC;
			q0:  out STD_LOGIC;
			q1:  out STD_LOGIC);
end entity;

architecture Dmux2way_FOLKS of Dmux2way is
begin
process (a,sel) is
begin
   if (sel = '0') then
	q0<=a;
	q1<='0';
	else 
	q1<=a;
	q0<='0';
	end if;
end process;
end Dmux2way_FOLKS;