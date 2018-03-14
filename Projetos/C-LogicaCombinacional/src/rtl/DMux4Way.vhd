library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity DMux4Way is
	port (
			a:   in  STD_LOGIC;
			sel: in  STD_LOGIC_VECTOR(1 downto 0);
			q0:  out STD_LOGIC;
			q1:  out STD_LOGIC;
			q2:  out STD_LOGIC;
			q3:  out STD_LOGIC);
end entity;
architecture Dmux4way_FOLKS of Dmux4way is
begin
process (a,sel) is
begin
	if (sel="00") then
	q0<=a;
	q1<='0';
	q2<='0';
	q3<='0';
	end if;
	if (sel="01") then
	q0<='0';
	q1<=a;
	q2<='0';
	q3<='0';
	end if;
	if (sel="10") then
	q0<='0';
	q1<='0';
	q2<=a;
	q3<='0';
	end if;
	if (sel="11") then
	q0<='0';
	q1<='0';
	q2<='0';
	q3<=a;
	end if;







end process;
end Dmux4way_FOLKS;
