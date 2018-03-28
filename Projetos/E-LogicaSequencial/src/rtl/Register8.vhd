-- Elementos de Sistemas
-- by Luciano Soares
-- Register8.vhd

Library ieee; 
use ieee.std_logic_1164.all;
  
entity Register8 is
	port(
		clock:   in STD_LOGIC;
		input:   in STD_LOGIC_VECTOR(7 downto 0);
		load:    in STD_LOGIC;
		output: out STD_LOGIC_VECTOR(7 downto 0)
	);
end entity;

architecture arch of Register8 is
--signal C,L : STD_LOGIC;
--signal D,Q : STD_LOGIC_VECTOR(7 downto 0);

component BinaryDigit is
 port(
	clock:   in STD_LOGIC;
	input:   in STD_LOGIC;
	load:    in STD_LOGIC;
	output: out STD_LOGIC
 );
end component;

begin
BD0: BinaryDigit port map (clock, input(0), load, output(0));
BD1: BinaryDigit port map (clock, input(1), load, output(1));
BD2: BinaryDigit port map (clock, input(2), load, output(2));
BD3: BinaryDigit port map (clock, input(3), load, output(3));
BD4: BinaryDigit port map (clock, input(4), load, output(4));
BD5: BinaryDigit port map (clock, input(5), load, output(5));
BD6: BinaryDigit port map (clock, input(6), load, output(6));
BD7: BinaryDigit port map (clock, input(7), load, output(7));


end architecture;
