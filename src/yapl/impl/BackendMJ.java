package yapl.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import yapl.interfaces.BackendBinSM;

public class BackendMJ implements BackendBinSM {
	private class Mj {
		static final byte PRINT = (byte)51;
		static final byte RETURN = (byte)47;
		static final byte ADD = (byte)23;
		static final byte SUB = (byte)24;
		static final byte MUL = (byte)25;
		static final byte DIV = (byte)26;
		static final byte REM = (byte)27;
		static final byte NEG = (byte)28;
		static final byte CONST = (byte)22;
	}
	private int codeSize = 0;
	private int dataSize = 0;
	private int entryAddress = 0;
	private List<Byte> code = new ArrayList<Byte>();
	//private ArrayLst<byte> code = new ArrayList<byte>();
	
	private void emit(byte value)
	{
		code.add(value);
	}
	private void emit(int value)
	{
		emit((byte)value);
	}
	
	@Override
	public void add() {
		emit(Mj.ADD);
	}

	@Override
	public void allocArray() {
		// TODO Auto-generated method stub

	}

	@Override
	public int allocStack(int words) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int allocStaticData(int words) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int allocStringConstant(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void and() {
		
	}

	@Override
	public void arrayLength() {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignLabel(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public int boolValue(boolean value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void branchIf(boolean value, String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callProc(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void div() {
		emit(Mj.DIV);
	}

	@Override
	public void enterProc(String label, int nParams, boolean main) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitProc(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void isEqual() {
		// TODO Auto-generated method stub

	}

	@Override
	public void isGreater() {
		// TODO Auto-generated method stub

	}

	@Override
	public void isGreaterOrEqual() {
		// TODO Auto-generated method stub

	}

	@Override
	public void isLess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void isLessOrEqual() {
		// TODO Auto-generated method stub

	}

	@Override
	public void jump(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadArrayElement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadConst(int value) {
		if (value < 6 && value >= 0)
		{
			emit(15 + value);
		}
		else if (value == -1)
			emit(21);
		else
		{
			emit(Mj.CONST);
			emit4(value);
		}
		
	}
	
	private void emit4(int value)
	{
		byte[] byteArray = intToByteArray(value);
		for(int i = 0; i < 4; i++)
			emit(byteArray[i]);
	}

	@Override
	public void loadWord(int addr, boolean staticData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mod() {
		emit(Mj.REM);
	}

	@Override
	public void mul() {
		emit(Mj.MUL);
	}

	@Override
	public void neg() {
		emit(Mj.NEG);
	}

	@Override
	public void or() {
		// TODO Auto-generated method stub

	}

	@Override
	public int paramOffset(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void storeArrayDim(int dim) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeArrayElement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeWord(int addr, boolean staticData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sub() {
		emit(Mj.SUB);
	}

	@Override
	public int wordSize() {
		return 4;
	}

	@Override
	public void writeInteger() {
		loadConst(0);
		emit(Mj.PRINT);
	}

	private static byte[] intToByteArray(int value) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((value >>> offset) & 0xFF);
        }
        return b;
    }
	
	@Override
	public void writeObjectFile(OutputStream outStream) throws IOException {
			
		emit(Mj.RETURN);
		outStream.write('M');
		outStream.write('J');
		outStream.write(intToByteArray(code.size()));
		outStream.write(intToByteArray(dataSize));
		outStream.write(intToByteArray(entryAddress));
		for(Byte b : code){
			outStream.write(b);
		}
		outStream.flush();
	}

	@Override
	public void writeString(int addr) {
		// TODO Auto-generated method stub

	}

}